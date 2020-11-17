package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueNoneAckConfiguration {

  private static final String QUEUE_NAME = "queue-none-ack";

  @Bean
  public Queue queueNoneAck() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
