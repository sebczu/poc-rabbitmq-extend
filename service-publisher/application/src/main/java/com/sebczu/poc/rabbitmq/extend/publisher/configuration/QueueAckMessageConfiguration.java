package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueAckMessageConfiguration {

  private static final String QUEUE_ACK_MESSAGE_NAME = "queue-ack-message";

  @Bean
  public Queue queueAckMessage() {
    return new Queue(QUEUE_ACK_MESSAGE_NAME, false, false, true);
  }

}
