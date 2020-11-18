package com.sebczu.poc.rabbitmq.extend.publisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/queue/deadletter")
@RequiredArgsConstructor
public class QueueDeadLetterController {

  private final RabbitTemplate rabbitTemplate;
  private final Queue queueDeadLetter;

  @PostMapping
  public void sendToQueue(@RequestParam(value = "message") String message) {
    rabbitTemplate.convertAndSend(queueDeadLetter.getName(), message);
    log.info("message: {} send into queue: {}", message, queueDeadLetter.getName());
  }

}
