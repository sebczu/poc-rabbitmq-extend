package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueAckMessageConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueAckMessageListener {

  @RabbitListener(queues = QueueAckMessageConfiguration.QUEUE_ACK_MESSAGE_NAME)
  private void queueAckMessageListener(Message message) {
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueAckMessageConfiguration.QUEUE_ACK_MESSAGE_NAME);
    //send in queue nack - queue will be try sending this message again
    throw new RuntimeException("queueAckMessageListener exception");
  }

}
