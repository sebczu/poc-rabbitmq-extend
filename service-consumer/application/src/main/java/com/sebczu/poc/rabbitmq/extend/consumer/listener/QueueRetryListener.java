package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueRetryConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueRetryListener {

  @RabbitListener(queues = QueueRetryConfiguration.QUEUE_NAME, containerFactory = "retryContainerFactory")
  private void queueRetryListener(Message message){
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueRetryConfiguration.QUEUE_NAME);
    throw new RuntimeException("queueRetryListener exception");
  }

}
