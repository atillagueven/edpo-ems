package ch.unisg.ems.inventory.messages;

import ch.unisg.ems.inventory.domain.Order;
import ch.unisg.ems.inventory.flow.InventoryFlowContext;
import ch.unisg.ems.inventory.persistence.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Component
public class MessageListener {

    private final String salesTopic = "ems-sales";
    private final String inventoryTopic = "ems-inventory";

    @Autowired
    private OrderRepository orderRepository;

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(id = "sales", topics = salesTopic)
    public void salesMessageReceived(String messagePayload, @Header("type") String messageType) throws Exception{
        System.out.println("Message received: ems-sales" );

        if("OrderReceivedEvent".equals(messageType)) {
            orderReceived(messagePayload);
        }

        else {
            System.out.println("Ignored message of type " + salesTopic);
        }


    }


    @Transactional
    @KafkaListener(id = "inventory", topics = inventoryTopic)
    public void inventoryMessageReceived(String messagePayload, @Header("type") String messageType) throws Exception{
        System.out.println("Message received: ems-inventory" );

        if ("ClientAppointmentReceivedEvent".equals(messageType)) {
            appointmentReceived(messagePayload);
        }
        else {
            System.out.println("Ignored message of type " + inventoryTopic);
        }


    }


    @Transactional
    public void orderReceived(String messagePayload) {
        System.out.println("New order received: " + messagePayload);
        try {
            Order order = new Order(messagePayload);

            orderRepository.save(order);


            String traceId = UUID.randomUUID().toString();
            InventoryFlowContext context = new InventoryFlowContext();
            context.setOfferId(order.getId());
            context.setTraceId(traceId);
            context.setOfferMessage("");
            context.setLoadProfile(order.getLoadProfile());
            context.setOfferAccepted(false);


            zeebe.newCreateInstanceCommand() //
                    .bpmnProcessId("inventory-service") //
                    .latestVersion() //
                    .variables(context.asMap()) //
                    .send().join();

        } catch (IOException e) {
            System.out.println("Error while receiving the order: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while starting the bpmn process: " + e.getMessage());
        }
    }

    @Transactional
    public void appointmentReceived(String messagePayload) throws Exception {
        //Here you would maybe we should read something from the payload:
        System.out.println("Appointment received: " + messagePayload);

        /**
        OrderReply orderReply = new OrderReply(messagePayload);

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("offerStatus",offerReply.getOfferAccepted() ? "accepted" : offerReply.getNewOfferRequested() ? "newOfferRequested" :"rejected");

        zeebe.newPublishMessageCommand() //
                .messageName("ClientAppointmentReveivedEvent")
                .correlationKey(orderReply.getOfferId())
                .variables(newVariables)
                .send().join();

        System.out.println("Correlated " + orderReply.getOfferId());
         **/
    }


}