package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

  public static final String QUEUE_NAME = "queue-message";

  @Bean
  public Queue queueMessage() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
