package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
public class QueueRetryConfiguration {

  public static final String QUEUE_NAME = "queue-retry";

  @Bean
  public RetryOperationsInterceptor retryInterceptor() {
    return RetryInterceptorBuilder.stateless()
        .backOffOptions(1000, 2.0, 10000)
        .maxAttempts(5)
        .build();
  }

  @Bean
  public SimpleRabbitListenerContainerFactory retryContainerFactory(ConnectionFactory connectionFactory, RetryOperationsInterceptor retryInterceptor) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setAdviceChain(retryInterceptor);
    return factory;
  }

  @Bean
  public Queue queueRetry() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
