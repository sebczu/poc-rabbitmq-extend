package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeDirectConfiguration {

  private static final String EXCHANGE_DIRECT_NAME = "exchange-direct";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(EXCHANGE_DIRECT_NAME);
  }

}
