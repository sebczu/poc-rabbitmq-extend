package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueManualAckConfiguration {

  public static final String QUEUE_NAME = "queue-manual-ack";

  @Bean
  public Queue queueManualAck() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
