package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeTopicConfiguration {

  private static final String EXCHANGE_TOPIC_NAME = "exchange-topic";

  @Bean
  public TopicExchange exchangeTopic() {
    return new TopicExchange(EXCHANGE_TOPIC_NAME);
  }

}
