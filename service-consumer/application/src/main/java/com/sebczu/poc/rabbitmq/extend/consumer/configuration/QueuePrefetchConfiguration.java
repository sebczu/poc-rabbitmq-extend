package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class QueuePrefetchConfiguration {

  public static final String QUEUE_NAME = "queue-prefetch";

  @Bean
  public Queue queuePrefetch() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer prefetchMessageListenerContainer(ConnectionFactory connectionFactory) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queuePrefetch());
    simpleMessageListenerContainer.setPrefetchCount(3);
    simpleMessageListenerContainer.setMessageListener(message -> {
      log.info("message property: {}", message.getMessageProperties());
      log.info("message: {} receive from queue: {}", new String(message.getBody()), QUEUE_NAME);
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        log.error("interrupted", e);
      }
      throw new RuntimeException();
    });
    return simpleMessageListenerContainer;
  }
}
