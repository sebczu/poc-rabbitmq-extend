package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import com.sebczu.poc.rabbitmq.extend.consumer.listener.QueueDeadLetterListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Slf4j
@Configuration
public class QueueDeadLetterConfiguration {

  public static final String QUEUE_NAME = "queue-exception";

  @Bean
  public RetryOperationsInterceptor deadLetterRetryInterceptor() {
    return RetryInterceptorBuilder.stateless()
        .backOffOptions(1000, 2.0, 10000)
        .maxAttempts(4)
        .recoverer(new RejectAndDontRequeueRecoverer())
        .build();
  }

  @Bean
  public DirectExchange deadLetterExchange() {
    return new DirectExchange("dead-letter-exchange");
  }

  @Bean
  public Queue deadLetterQueue() {
    return new Queue("dead-letter-queue", false, false, true);
  }

  @Bean
  public Binding deadLetterBinding() {
    return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
  }

  @Bean
  public Queue exceptionQueue() {
    return QueueBuilder.nonDurable(QUEUE_NAME)
        .autoDelete()
        .deadLetterExchange("dead-letter-exchange")
        .deadLetterRoutingKey("deadLetter")
        .build();
  }

  @Bean
  public MessageListenerContainer deadLetterListenerContainer(ConnectionFactory connectionFactory,
      RetryOperationsInterceptor deadLetterRetryInterceptor, QueueDeadLetterListener listener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setAdviceChain(deadLetterRetryInterceptor);
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(exceptionQueue());
    simpleMessageListenerContainer.setMessageListener(listener);
    return simpleMessageListenerContainer;
  }

}
