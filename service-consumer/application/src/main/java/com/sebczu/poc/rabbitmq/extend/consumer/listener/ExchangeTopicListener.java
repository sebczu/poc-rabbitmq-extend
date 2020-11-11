package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangeTopicListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    log.info("message: {}, routing: {}", new String(message.getBody()), message.getMessageProperties().getReceivedRoutingKey());
  }
}
