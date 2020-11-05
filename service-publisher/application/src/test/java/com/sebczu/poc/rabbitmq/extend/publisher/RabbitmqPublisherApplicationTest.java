package com.sebczu.poc.rabbitmq.extend.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.RabbitMQContainer;

@Slf4j
@SpringBootTest
@ContextConfiguration(initializers = {RabbitmqPublisherApplicationTest.ContextInitializer.class})
@AutoConfigureMockMvc
public abstract class RabbitmqPublisherApplicationTest {

  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected RabbitTemplate rabbitTemplate;

  static class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext applicationContext) {
      log.info("context init");
      RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8.9-management")
          .withExposedPorts(5672)
          .withUser("admin", "test")
          .withPermission("/", "admin", ".*", ".*", ".*");

      rabbitMQContainer.start();

      TestPropertyValues.of(
          "spring.rabbitmq.port=" + rabbitMQContainer.getAmqpPort()
      ).applyTo(applicationContext);
    }
  }

}
