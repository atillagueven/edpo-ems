package ch.unisg.ems.inventory;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = "inventory_service.bpmn")
public class InventoryApplication {

  public static void main(String[] args) {
    SpringApplication.run(InventoryApplication.class);
  }

}