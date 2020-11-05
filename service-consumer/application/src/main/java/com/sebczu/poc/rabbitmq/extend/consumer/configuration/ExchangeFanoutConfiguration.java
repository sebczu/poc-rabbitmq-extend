package com.sebczu.poc.rabbitmq.extend.consumer.configuration;

import com.sebczu.poc.rabbitmq.extend.consumer.listener.ExchangeFanoutListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
public class ExchangeFanoutConfiguration {

  public static final String QUEUE_FANOUT_NAME = "queue-fanout-" + UUID.randomUUID().toString();
  private static final String EXCHANGE_FANOUT_NAME = "exchange-fanout";

  @Bean
  public Queue queueFanout() {
    return new Queue(QUEUE_FANOUT_NAME, false, true, true);
  }

  @Bean
  public FanoutExchange exchangeFanout() {
    return new FanoutExchange(EXCHANGE_FANOUT_NAME);
  }

  @Bean
  public Declarables bindingFanout() {
    return new Declarables(
        BindingBuilder.bind(queueFanout()).to(exchangeFanout())
    );
  }

  //dynamic listener
  @Bean
  public MessageListenerContainer fanoutMessageListenerContainer(ConnectionFactory connectionFactory, ExchangeFanoutListener exchangeFanoutListener) {
    SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setListenerId("fanout-listener");
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queueFanout());
    simpleMessageListenerContainer.setMessageListener(exchangeFanoutListener);
    return simpleMessageListenerContainer;
  }
}
