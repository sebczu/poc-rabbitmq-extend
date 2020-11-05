package com.sebczu.poc.rabbitmq.extend.publisher;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueueControllerTest extends RabbitmqPublisherApplicationTest {

  private static final String QUEUE_MESSAGE_NAME = "queue-message";

  @Test
  public void shouldSendMessageIntoQueue() throws Exception {
    String message = "test";

    mockMvc.perform(post("/queue")
        .param("message", message))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));

    Object messageResult = rabbitTemplate.receiveAndConvert(QUEUE_MESSAGE_NAME);
    assertThat(messageResult)
        .isNotNull()
        .isEqualTo(message);
  }

}
