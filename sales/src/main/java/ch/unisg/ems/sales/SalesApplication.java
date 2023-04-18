package ch.unisg.ems.sales;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = "sales_service.bpmn")
public class SalesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesApplication.class);
  }

}