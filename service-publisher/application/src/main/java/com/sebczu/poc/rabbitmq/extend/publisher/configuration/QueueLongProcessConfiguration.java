package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueLongProcessConfiguration {

  private static final String QUEUE_NAME = "queue-longprocess";

  @Bean
  public Queue queueLongProcess() {
    return new Queue(QUEUE_NAME, true, false, true);
  }

}
