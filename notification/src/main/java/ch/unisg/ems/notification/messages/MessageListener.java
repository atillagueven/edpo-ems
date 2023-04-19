package ch.unisg.ems.notification.messages;

import ch.unisg.ems.notification.application.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.handler.annotation.Header;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class MessageListener {
    public static final String TOPIC_NAME = "ems-notification";
  @Autowired
  private NotificationService notificationService;

  @Autowired
  private ObjectMapper objectMapper;

  @Transactional
  @KafkaListener(id = "notification", topics = TOPIC_NAME)
  public void orderUpdated(String messageJson, @Header("type") String messageType) throws Exception {
      System.out.println("Received message: " + messageType);

      if ("SendOfferToClientCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);
          JsonNode message = objectMapper.readTree(messageJson);
          String offerId = message.get("data").get("offerId").asText();
          String clientEmail = message.get("data").get("clientEmail").asText();
          String clientName = message.get("data").get("clientName").asText();
          String offerMessage = message.get("data").get("offerMessage").asText();

          String emailContent = "Dear " + clientName + ",\n\n"
                  + offerMessage + "\n\n"
                  + "Please click on the following link to accept the offer:\n"
                  + "http://localhost:3000/sales/offer-reply?id=" + offerId + "\n\n"
                  + "Best regards,\n" + "EMS Team";

          notificationService.sendEmail(clientEmail, emailContent);
      } else if ("SendReminderToClientCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);
          System.out.println("Received message: " + messageType);
          JsonNode message = objectMapper.readTree(messageJson);
          String offerId = message.get("data").get("offerId").asText();
          String clientEmail = message.get("data").get("clientEmail").asText();
          String clientName = message.get("data").get("clientName").asText();
          String offerMessage = message.get("data").get("offerMessage").asText();

          String emailContent = "Dear " + clientName + ",\n\n"
                  + offerMessage + "\n\n"
                  + "REMINDER \n"
                  + "Please click on the following link to accept the offer:\n"
                  + "http://localhost:3000/sales/offer-reply?id=" + offerId + "\n\n"
                  + "Best regards,\n" + "EMS Team";

          notificationService.sendEmail(clientEmail, emailContent);
      } else if ("SendInvoiceToClientCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);
          System.out.println("Received message: " + messageType);
          JsonNode message = objectMapper.readTree(messageJson);
          String offerId = message.get("data").get("offerId").asText();
          String clientEmail = message.get("data").get("clientEmail").asText();

          String emailContent = "You have a new Invoice for order : "+ offerId +".\n"
                  + "Please pay your invoice in the next 14 days.\n\n"
                  + "Best regards,\n" + "EMS Team";

          notificationService.sendEmail(clientEmail, emailContent);

      } else if ("SendInvoiceReminderToClientCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);
          JsonNode message = objectMapper.readTree(messageJson);
          String offerId = message.get("data").get("offerId").asText();
          String clientEmail = message.get("data").get("clientEmail").asText();

          String emailContent = "We noticed, that the there is still an outstanding invoice for you order : "+ offerId +".\n"
                  + "Please pay your invoice in the next 14 days.\n\n"
                  + "Best regards,\n" + "EMS Team";

          notificationService.sendEmail(clientEmail, emailContent);

      } else if ("SendAssemblyInfoToClientCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);

          JsonNode message = objectMapper.readTree(messageJson);
          String offerId = message.get("data").get("offerId").asText();
          String clientEmail = message.get("data").get("clientEmail").asText();
          String clientName = message.get("data").get("clientName").asText();
          String offerMessage = message.get("data").get("offerMessage").asText();
          String emsSystemRecommendation = message.get("data").get("emsSystemRecommendation").asText();

          String emailContent = "Dear " + clientName + ",\n\n"
                  + offerMessage + "\n\n"
                  + "Please click on the following link to arrange an appointment:\n"
                  + "http://localhost:3000/inventory?id=" + offerId + "\n\n"
                  + "Best regards,\n" + "EMS Team";

          notificationService.sendEmail(clientEmail, emailContent);

      } else if ("SendNotificationToEngineerCommand".equals(messageType)) {
          System.out.println("Received message: " + messageType);
          String emailContent = "Dear Engineer\n\n"
                  + "There is a new task for you.\n"
                  + "Best regards,\n" + "EMS Team";
          notificationService.sendEmail("engineer@ems.ch", emailContent);

      }
      else {
            System.out.println("Received unknown message: " + messageType);
      }
  }
    
}
