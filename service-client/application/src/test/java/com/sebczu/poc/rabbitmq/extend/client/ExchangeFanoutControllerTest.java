package com.sebczu.poc.rabbitmq.extend.client;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExchangeFanoutControllerTest extends RabbitmqClientApplicationTest {

  private static final String QUEUE_FANOUT_NAME_1 = "queue-fanout-1";
  private static final String QUEUE_FANOUT_NAME_2 = "queue-fanout-2";
  private static final String QUEUE_FANOUT_NAME_3 = "queue-fanout-3";

  @Autowired
  private AmqpAdmin admin;

  @Test
  public void shouldSendMessageIntoExchangeFanout() throws Exception {
    admin.declareQueue(queueFanout1());
    admin.declareQueue(queueFanout2());
    admin.declareQueue(queueFanout3());
    admin.declareBinding(BindingBuilder.bind(queueFanout1()).to(exchangeFanout()));
    admin.declareBinding(BindingBuilder.bind(queueFanout2()).to(exchangeFanout()));

    String message = "test";

    mockMvc.perform(post("/exchange/fanout")
        .param("message", message))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    Object messageResultQueue1 = rabbitTemplate.receiveAndConvert(QUEUE_FANOUT_NAME_1);
    assertThat(messageResultQueue1)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue2 = rabbitTemplate.receiveAndConvert(QUEUE_FANOUT_NAME_2);
    assertThat(messageResultQueue2)
        .isNotNull()
        .isEqualTo(message);

    Object messageResultQueue3 = rabbitTemplate.receiveAndConvert(QUEUE_FANOUT_NAME_3);

    //queue not binding to exchange fanout
    assertThat(messageResultQueue3)
        .isNull();
  }

  public Queue queueFanout1() {
    return new Queue(QUEUE_FANOUT_NAME_1, false, true, true);
  }

  public Queue queueFanout2() {
    return new Queue(QUEUE_FANOUT_NAME_2, false, true, true);
  }

  public Queue queueFanout3() {
    return new Queue(QUEUE_FANOUT_NAME_3, false, true, true);
  }

  public FanoutExchange exchangeFanout() {
    return new FanoutExchange("exchange-fanout");
  }

}
