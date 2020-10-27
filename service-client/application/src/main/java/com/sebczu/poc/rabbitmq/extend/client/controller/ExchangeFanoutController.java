package com.sebczu.poc.rabbitmq.extend.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exchange/fanout")
@RequiredArgsConstructor
public class ExchangeFanoutController {

  private final RabbitTemplate rabbitTemplate;
  private final FanoutExchange exchangeFanout;

  @PostMapping
  public void sendToExchange(@RequestParam(value = "message") String message) {
    rabbitTemplate.convertAndSend(exchangeFanout.getName(), "", message);
    log.info("message: {} send into exchange: {}", message, exchangeFanout.getName());
  }

}
