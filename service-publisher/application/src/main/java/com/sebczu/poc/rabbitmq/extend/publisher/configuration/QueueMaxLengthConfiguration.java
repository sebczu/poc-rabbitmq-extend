package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueMaxLengthConfiguration {

  public static final String QUEUE_NAME = "queue-max-length";

  @Bean
  public Queue queueMaxLength() {
    return QueueBuilder.nonDurable(QUEUE_NAME)
        .autoDelete()
        .maxLength(3)
        .build();
  }

}
