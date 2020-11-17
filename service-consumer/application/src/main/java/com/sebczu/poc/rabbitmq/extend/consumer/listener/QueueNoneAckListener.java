package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueNoneAckConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueNoneAckListener {

  @RabbitListener(queues = QueueNoneAckConfiguration.QUEUE_NAME, ackMode = "NONE")
  private void queueNoneAckListener(Message message) {
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueNoneAckConfiguration.QUEUE_NAME);
    throw new RuntimeException("queueNoneAckListener exception");
  }

}
