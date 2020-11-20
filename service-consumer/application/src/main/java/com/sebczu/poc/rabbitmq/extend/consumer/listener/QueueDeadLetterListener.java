package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueDeadLetterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueDeadLetterListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    log.info("message property: {}", message.getMessageProperties());
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueDeadLetterConfiguration.QUEUE_NAME);
    throw new RuntimeException("queueDeadLetterListener exception");
  }
}
