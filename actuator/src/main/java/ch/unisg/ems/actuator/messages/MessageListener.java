package ch.unisg.ems.actuator.messages;

import ch.unisg.ems.actuator.messages.MessageSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageListener {

    private final String productionTopic = "pv_production_clean";

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(id = "pv_prod", topics = productionTopic)
    public void messageReceived(String messagePayload, @Header("type") String messageType) throws Exception{
        System.out.println("Message received: PvProductionCleaned topic" );

        System.out.println("Message payload: " + messagePayload);
        if("OfferRequestedEvent".equals(messageType)) {
            //offerRequested(messagePayload);
        }
        else if ("ClientAnswerReceivedEvent".equals(messageType)) {
            //clientAnswerReceived(messagePayload);
        }
        else {
            System.out.println("Ignored message of type " + productionTopic );
        }
    }
}