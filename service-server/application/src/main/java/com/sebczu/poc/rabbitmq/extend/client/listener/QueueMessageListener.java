package com.sebczu.poc.rabbitmq.extend.client.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueMessageListener {

  private static final String QUEUE_MESSAGE_NAME = "queue-message";

  @RabbitListener(queues = QUEUE_MESSAGE_NAME)
  private void queueMessageListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QUEUE_MESSAGE_NAME);
  }

}
