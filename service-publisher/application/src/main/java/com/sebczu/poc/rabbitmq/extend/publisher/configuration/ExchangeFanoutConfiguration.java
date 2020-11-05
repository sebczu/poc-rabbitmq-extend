package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeFanoutConfiguration {

  private static final String EXCHANGE_FANOUT_NAME = "exchange-fanout";

  @Bean
  public FanoutExchange exchangeFanout() {
    return new FanoutExchange(EXCHANGE_FANOUT_NAME);
  }

}
