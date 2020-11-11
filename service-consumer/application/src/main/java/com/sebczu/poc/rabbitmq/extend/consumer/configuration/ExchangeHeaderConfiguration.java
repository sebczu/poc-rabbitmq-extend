package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import com.sebczu.poc.rabbitmq.extend.consumer.listener.ExchangeHeaderListener;
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
public class ExchangeHeaderConfiguration {

  public static final String QUEUE_HEADER_ALL = "queue-header-all-" + UUID.randomUUID().toString();
  public static final String QUEUE_HEADER_ADMIN = "queue-header-admin-" + UUID.randomUUID().toString();
  public static final String QUEUE_HEADER_SIMPLE = "queue-header-simple-" + UUID.randomUUID().toString();
  private static final String EXCHANGE_HEADER_NAME = "exchange-header";

  @Bean
  public Queue queueHeaderAll() {
    return new Queue(QUEUE_HEADER_ALL, false, true, true);
  }

  @Bean
  public Queue queueHeaderAdmin() {
    return new Queue(QUEUE_HEADER_ADMIN, false, true, true);
  }

  @Bean
  public Queue queueHeaderSimple() {
    return new Queue(QUEUE_HEADER_SIMPLE, false, true, true);
  }

  @Bean
  public HeadersExchange exchangeHeader() {
    return new HeadersExchange(EXCHANGE_HEADER_NAME);
  }

  @Bean
  public Declarables bindingHeader() {
    return new Declarables(
        BindingBuilder.bind(queueHeaderAll()).to(exchangeHeader()).where("user").exists(),
        BindingBuilder.bind(queueHeaderAdmin()).to(exchangeHeader()).where("user").matches("admin"),
        BindingBuilder.bind(queueHeaderSimple()).to(exchangeHeader()).where("user").matches("simple")
    );
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer headerAllMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeHeaderListener exchangeHeaderListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("header-listener-all");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueHeaderAll());
    simpleMessageListenerContainer.setMessageListener(exchangeHeaderListener);
    return simpleMessageListenerContainer;
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer headerAdminMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeHeaderListener exchangeHeaderListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("header-listener-admin");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueHeaderAdmin());
    simpleMessageListenerContainer.setMessageListener(exchangeHeaderListener);
    return simpleMessageListenerContainer;
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer headerSimpleMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeHeaderListener exchangeHeaderListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("header-listener-simple");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueHeaderSimple());
    simpleMessageListenerContainer.setMessageListener(exchangeHeaderListener);
    return simpleMessageListenerContainer;
  }
}
