package com.sebczu.poc.rabbitmq.extend.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exchange/direct")
@RequiredArgsConstructor
public class ExchangeDirectController {

  private final RabbitTemplate rabbitTemplate;
  private final DirectExchange directExchange;

  @PostMapping
  public void sendToExchange(@RequestParam(value = "routing") String routing, @RequestParam(value = "message") String message) {
    rabbitTemplate.convertAndSend(directExchange.getName(), routing, message);
    log.info("message: {} send into exchange: {}", message, directExchange.getName());
  }

}
