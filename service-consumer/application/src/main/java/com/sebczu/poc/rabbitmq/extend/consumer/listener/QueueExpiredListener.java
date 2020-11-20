package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueConfiguration;
import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueExpiredConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueExpiredListener {

  @RabbitListener(queues = QueueExpiredConfiguration.QUEUE_NAME)
  private void queueExpiredListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueConfiguration.QUEUE_NAME);
  }

}
