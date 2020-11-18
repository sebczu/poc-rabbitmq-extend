package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueConcurrentConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueConcurrentListener {

  @RabbitListener(queues = QueueConcurrentConfiguration.QUEUE_NAME, concurrency = "3")
  private void queueConcurrentListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueConcurrentConfiguration.QUEUE_NAME);
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("send ack");
  }

}
