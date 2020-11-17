package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueRetryConfiguration {

  public static final String QUEUE_NAME = "queue-retry";

  @Bean
  public Queue queueRetrying() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
