package com.sebczu.poc.rabbitmq.extend.publisher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exchange/header")
@RequiredArgsConstructor
public class ExchangeHeaderController {

  private final RabbitTemplate rabbitTemplate;
  private final HeadersExchange headerExchange;

  @PostMapping
  public void sendToExchange(@RequestParam(value = "userType") String userType, @RequestParam(value = "message") String message) {
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setHeader("user", userType);
    MessageConverter messageConverter = new SimpleMessageConverter();
    Message messageToSend = messageConverter.toMessage(message, messageProperties);

    rabbitTemplate.convertAndSend(headerExchange.getName(), "", messageToSend);
    log.info("message: {} send into exchange: {}", message, headerExchange.getName());
  }

}
