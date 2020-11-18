package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueLongProcessConfiguration {

  public static final String QUEUE_NAME = "queue-longprocess";

  @Bean
  public Queue queueLongProcess() {
    return new Queue(QUEUE_NAME, true, false, true);
  }

}
