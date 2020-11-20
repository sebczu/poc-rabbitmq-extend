package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueLongProcessConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueLongProcessListener {

  @RabbitListener(queues = QueueLongProcessConfiguration.QUEUE_NAME)
  private void queueMessageListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueLongProcessConfiguration.QUEUE_NAME);
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      log.error("interrupted", e);
    }
    log.info("send ack");
  }

}
