package com.sebczu.poc.rabbitmq.extend.client.listener;

import com.sebczu.poc.rabbitmq.extend.client.configuration.QueueMessageConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueMessageListener {

  @RabbitListener(queues = QueueMessageConfiguration.QUEUE_MESSAGE_NAME)
  private void queueMessageListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueMessageConfiguration.QUEUE_MESSAGE_NAME);
  }

}
