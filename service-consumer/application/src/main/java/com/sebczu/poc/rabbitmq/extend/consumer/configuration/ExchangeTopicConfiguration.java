package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import com.sebczu.poc.rabbitmq.extend.consumer.listener.ExchangeTopicListener;
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
public class ExchangeTopicConfiguration {

  public static final String QUEUE_TOPIC_1_NAME = "queue-topic-1-" + UUID.randomUUID().toString();
  public static final String QUEUE_TOPIC_ALL_NAME = "queue-topic-all-" + UUID.randomUUID().toString();
  private static final String EXCHANGE_TOPIC_NAME = "exchange-topic";
  private static final String EXCHANGE_TOPIC_ROUTE_1 = "test.1";
  private static final String EXCHANGE_TOPIC_ROUTE_ALL = "test.*";

  @Bean
  public Queue queueTopic1() {
    return new Queue(QUEUE_TOPIC_1_NAME, false, true, true);
  }

  @Bean
  public Queue queueTopicAll() {
    return new Queue(QUEUE_TOPIC_ALL_NAME, false, true, true);
  }

  @Bean
  public TopicExchange exchangeTopic() {
    return new TopicExchange(EXCHANGE_TOPIC_NAME);
  }

  @Bean
  public Declarables bindingTopic() {
    return new Declarables(
        BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with(EXCHANGE_TOPIC_ROUTE_1),
        BindingBuilder.bind(queueTopicAll()).to(exchangeTopic()).with(EXCHANGE_TOPIC_ROUTE_ALL)
    );
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer topic1MessageListenerContainer(ConnectionFactory connectionFactory, ExchangeTopicListener exchangeTopicListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("topic-listener-1");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueTopic1());
    simpleMessageListenerContainer.setMessageListener(exchangeTopicListener);
    return simpleMessageListenerContainer;
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer topicAllMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeTopicListener exchangeTopicListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("topic-listener-all");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueTopicAll());
    simpleMessageListenerContainer.setMessageListener(exchangeTopicListener);
    return simpleMessageListenerContainer;
  }
}
