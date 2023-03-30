package ch.unisg.ems.inventory;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("application")
public class InventoryApplication {

  public static void main(String[] args) {
    SpringApplication.run(InventoryApplication.class);
  }

}