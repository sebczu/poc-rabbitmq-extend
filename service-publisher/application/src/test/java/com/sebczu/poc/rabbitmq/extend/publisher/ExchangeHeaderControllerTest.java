package com.sebczu.poc.rabbitmq.extend.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExchangeHeaderControllerTest extends RabbitmqPublisherApplicationTest {

  private static final String QUEUE_NAME_1 = "queue-1";
  private static final String QUEUE_NAME_2 = "queue-2";
  private static final String QUEUE_NAME_3 = "queue-3";
  private static final String QUEUE_NAME_4 = "queue-4";

  @Autowired
  private AmqpAdmin admin;

  @Test
  public void shouldSendMessageIntoExchangeHeader() throws Exception {
    admin.declareQueue(queue1());
    admin.declareQueue(queue2());
    admin.declareQueue(queue3());
    admin.declareQueue(queue4());
    admin.declareBinding(BindingBuilder.bind(queue1()).to(exchange()).where("user").exists());
    admin.declareBinding(BindingBuilder.bind(queue2()).to(exchange()).where("user").exists());
    admin.declareBinding(BindingBuilder.bind(queue3()).to(exchange()).where("headerInvalid").exists());

    String message = "test";

    mockMvc.perform(post("/exchange/header")
        .param("userType", "any")
        .param("message", message))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    Object messageResultQueue1 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_1);
    assertThat(messageResultQueue1)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue2 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_2);
    assertThat(messageResultQueue2)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue3 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_3);

    //different routing
    assertThat(messageResultQueue3)
        .isNull();

    Object messageResultQueue4 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_4);

    //queue not binding to exchange
    assertThat(messageResultQueue4)
        .isNull();
  }

  @Test
  public void shouldSendMessageIntoExchangeHeaderWithHeaderAny() throws Exception {
    admin.declareQueue(queue1());
    admin.declareQueue(queue2());
    admin.declareBinding(BindingBuilder.bind(queue1()).to(exchange()).where("user").exists());
    admin.declareBinding(BindingBuilder.bind(queue2()).to(exchange()).where("user").matches("admin"));

    String message = "test";

    mockMvc.perform(post("/exchange/header")
        .param("userType", "admin")
        .param("message", message))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    Object messageResultQueue1 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_1);
    assertThat(messageResultQueue1)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue2 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_2);
    assertThat(messageResultQueue2)
        .isNotNull()
        .isEqualTo(message);
  }

  @Test
  public void shouldSendMessageIntoExchangeHeaderWithHeaderSpecific() throws Exception {
    admin.declareQueue(queue1());
    admin.declareQueue(queue2());
    admin.declareBinding(BindingBuilder.bind(queue1()).to(exchange()).where("user").exists());
    admin.declareBinding(BindingBuilder.bind(queue2()).to(exchange()).where("user").matches("admin"));

    String message = "test";

    mockMvc.perform(post("/exchange/header")
        .param("userType", "simple")
        .param("message", message))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    Object messageResultQueue1 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_1);
    assertThat(messageResultQueue1)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue2 = rabbitTemplate.receiveAndConvert(QUEUE_NAME_2);
    assertThat(messageResultQueue2)
        .isNull();
  }

  public Queue queue1() {
    return new Queue(QUEUE_NAME_1, false, true, true);
  }

  public Queue queue2() {
    return new Queue(QUEUE_NAME_2, false, true, true);
  }

  public Queue queue3() {
    return new Queue(QUEUE_NAME_3, false, true, true);
  }

  public Queue queue4() {
    return new Queue(QUEUE_NAME_4, false, true, true);
  }

  public HeadersExchange exchange() {
    return new HeadersExchange("exchange-header");
  }

}
