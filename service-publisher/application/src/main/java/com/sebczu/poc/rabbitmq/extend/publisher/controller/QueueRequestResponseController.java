package com.sebczu.poc.rabbitmq.extend.publisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/queue/requestresponse")
@RequiredArgsConstructor
public class QueueRequestResponseController {

  private final RabbitTemplate rabbitTemplate;
  private final Queue queueRequestResponse;

  @PostMapping
  public void sendToQueue(@RequestParam(value = "message") String message) {
    log.info("message: {} send into queue: {}", message, queueRequestResponse.getName());

    MessagePostProcessor messagePostProcessor = messageInProcess -> {
      log.info("message property: {}", messageInProcess.getMessageProperties());
      return messageInProcess;
    };

    String responseMessage = (String) rabbitTemplate.convertSendAndReceive(queueRequestResponse.getName(), (Object)message, messagePostProcessor);
    log.info("message response: {}", responseMessage);
  }

}
