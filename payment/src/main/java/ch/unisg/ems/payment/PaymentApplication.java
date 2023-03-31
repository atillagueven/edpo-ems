package ch.unisg.ems.payment;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@EnableZeebeClient
@ZeebeDeployment(resources = "classpath:converted-c8-power_consumption_Payment_Service.bpmn")
public class PaymentApplication {
  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class);
  }
}