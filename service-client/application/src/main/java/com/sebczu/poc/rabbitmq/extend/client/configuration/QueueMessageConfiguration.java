package com.sebczu.poc.rabbitmq.extend.client.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueMessageConfiguration {

  private static final String QUEUE_MESSAGE_NAME = "queue-message";

  @Bean
  public Queue queueMessage() {
    return new Queue(QUEUE_MESSAGE_NAME, false);
  }

}
