package com.sebczu.poc.rabbitmq.extend.publisher.configuration;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeHeaderConfiguration {

  private static final String EXCHANGE_HEADER_NAME = "exchange-header";

  @Bean
  public HeadersExchange exchangeHeader() {
    return new HeadersExchange(EXCHANGE_HEADER_NAME);
  }

}
