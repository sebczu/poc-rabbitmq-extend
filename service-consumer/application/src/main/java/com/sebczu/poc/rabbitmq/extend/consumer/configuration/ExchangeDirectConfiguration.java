package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import com.sebczu.poc.rabbitmq.extend.consumer.listener.ExchangeDirectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
public class ExchangeDirectConfiguration {

  public static final String QUEUE_DIRECT_NAME = "queue-direct-" + UUID.randomUUID().toString();
  private static final String EXCHANGE_DIRECT_NAME = "exchange-direct";
  private static final String EXCHANGE_DIRECT_ROUTE = "test";

  @Bean
  public Queue queueDirect() {
    return new Queue(QUEUE_DIRECT_NAME, false, true, true);
  }

  @Bean
  public DirectExchange exchangeDirect() {
    return new DirectExchange(EXCHANGE_DIRECT_NAME);
  }

  @Bean
  public Declarables bindingDirect() {
    return new Declarables(
        BindingBuilder.bind(queueDirect()).to(exchangeDirect()).with(EXCHANGE_DIRECT_ROUTE)
    );
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer directMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeDirectListener exchangeDirectListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("direct-listener");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueDirect());
    simpleMessageListenerContainer.setMessageListener(exchangeDirectListener);
    return simpleMessageListenerContainer;
  }
}
