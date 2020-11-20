package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class QueuePrefetchConfiguration {

  public static final String QUEUE_NAME = "queue-prefetch";

  @Bean
  public Queue queuePrefetch() {
    return new Queue(QUEUE_NAME, false, false, true);
  }

}
