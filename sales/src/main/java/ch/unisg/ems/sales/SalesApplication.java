package ch.unisg.ems.sales;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class SalesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesApplication.class);
  }

}