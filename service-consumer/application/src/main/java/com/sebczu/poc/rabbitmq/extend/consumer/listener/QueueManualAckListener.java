package com.sebczu.poc.rabbitmq.extend.consumer.listener;

import com.rabbitmq.client.Channel;
import com.sebczu.poc.rabbitmq.extend.consumer.configuration.QueueManualAckConfiguration;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueManualAckListener {

  @RabbitListener(queues = QueueManualAckConfiguration.QUEUE_NAME, ackMode = "MANUAL")
  private void queueManualAckListener(Message message, Channel channel) {
    log.info("message: {} receive from queue: {}", new String(message.getBody()), QueueManualAckConfiguration.QUEUE_NAME);
    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      log.error("cannot send ack", e);
    }
  }

}
