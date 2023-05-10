package ch.unisg.ems.monitoringbackend.messages.kafka;

import ch.unisg.ems.monitoringbackend.messages.websocket.ProductionEventMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class MessageListener {
    public static final String TOPIC_NAME = "pv_production";


  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  SimpMessagingTemplate template;

  @Transactional
  @KafkaListener(id = "monitoring-backend", topics = TOPIC_NAME)
  public void handle(String messageJson) throws Exception {
      System.out.println("Received message: " + messageJson);
      ObjectMapper objectMapper = new ObjectMapper();
      ProductionEventMessage productionEventMessage = objectMapper.readValue(messageJson, ProductionEventMessage.class);
      System.out.println("Sending message to Websocket topic '/topic/production'");
      template.convertAndSend("/topic/production", productionEventMessage);
  }


}
