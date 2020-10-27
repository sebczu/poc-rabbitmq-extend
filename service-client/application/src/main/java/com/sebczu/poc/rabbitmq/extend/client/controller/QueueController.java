package com.sebczu.poc.rabbitmq.extend.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {

  private final RabbitTemplate rabbitTemplate;
  private final Queue queueMessage;

  @PostMapping
  public void sendToQueue(@RequestParam(value = "message") String message) {
    rabbitTemplate.convertAndSend(queueMessage.getName(), message);
    log.info("message: {} send into queue: {}", message, queueMessage.getName());
  }

}
