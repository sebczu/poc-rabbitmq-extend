package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueDeadLetterConfiguration {

  public static final String QUEUE_NAME = "queue-exception";

  @Bean
  public Queue exceptionQueue() {
    return QueueBuilder.nonDurable(QUEUE_NAME)
        .autoDelete()
        .deadLetterExchange("dead-letter-exchange")
        .deadLetterRoutingKey("deadLetter")
        .build();
  }
}
