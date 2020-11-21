package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueRequestResponseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueRequestResponseListener {

  @RabbitListener(queues = QueueRequestResponseConfiguration.QUEUE_NAME)
  private String queueRequestResponseListener(Message message) {
    log.info("message property: {}", message.getMessageProperties());
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueRequestResponseConfiguration.QUEUE_NAME);
    return "hello " + new String(message.getBody());
  }

}
