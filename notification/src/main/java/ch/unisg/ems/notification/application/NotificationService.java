package ch.unisg.ems.notification.application;

import org.springframework.stereotype.Component;


@Component
public class NotificationService {

  public void sendEmail(String recipient, String emailContent) {
      System.out.println("\n");
      System.out.println("Sending email to " + recipient);
      System.out.println("\n");
      System.out.println(emailContent);
      System.out.println("\n");
    // TODO: send email
  }



}
