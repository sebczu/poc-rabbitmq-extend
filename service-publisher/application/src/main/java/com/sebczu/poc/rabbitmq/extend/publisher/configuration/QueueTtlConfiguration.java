package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueTtlConfiguration {

  public static final String QUEUE_NAME = "queue-ttl";

  @Bean
  public Queue queueTtl() {
    return QueueBuilder.nonDurable(QUEUE_NAME)
        .autoDelete()
        .ttl(7000)
        .build();
  }

}
