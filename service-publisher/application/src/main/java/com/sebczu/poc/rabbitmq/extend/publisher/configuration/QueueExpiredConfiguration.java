package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueExpiredConfiguration {

  public static final String QUEUE_NAME = "queue-expired";

  @Bean
  public Queue queueExpired() {
    return QueueBuilder.nonDurable(QUEUE_NAME)
        .expires(10000)
        .build();
  }

}
