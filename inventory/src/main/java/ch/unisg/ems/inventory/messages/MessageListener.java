package ch.unisg.ems.inventory.messages;

import ch.unisg.ems.inventory.domain.AppointmentReply;
import ch.unisg.ems.inventory.domain.ComplementaryMaterialsReceivedDto;
import ch.unisg.ems.inventory.domain.Order;
import ch.unisg.ems.inventory.domain.PartsReceivedDto;
import ch.unisg.ems.inventory.flow.InventoryFlowContext;
import ch.unisg.ems.inventory.persistence.OrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
    @KafkaListener(id = "sales-inventory", topics = salesTopic)
    public void salesMessageReceived(String messagePayload, @Header("type") String messageType) throws Exception{
        System.out.println("Message received: ems-sales" );

        if("OrderPlacedEvent".equals(messageType)) {
            orderReceived(messagePayload);
        } else if("appointmentReceived".equals(messageType)) {
            appointmentReceived(messagePayload);
        } else {
            System.out.println("Ignored message of type " + messageType);
        }


    }


    @Transactional
    @KafkaListener(id = "inventory", topics = inventoryTopic)
    public void inventoryMessageReceived(String messagePayload, @Header("type") String messageType) throws Exception{
        System.out.println("Message received: ems-inventory" + messagePayload);

        if ("ClientAppointmentReceivedEvent".equals(messageType)) {
            appointmentReceived(messagePayload);
        } else if("PartsReceivedEvent".equals(messageType)) {
            partsReceived(messagePayload);
        } else if("ComplementaryMaterialsReceivedEvent".equals(messageType)) {
            complementaryMaterialsReceived(messagePayload);
        } else {
            System.out.println("Ignored message of type " + messageType);
        }
    }


    @Transactional
    public void orderReceived(String messagePayload) {
        System.out.println("New order received: " + messagePayload);
        try {
            JsonNode message = objectMapper.readTree(messagePayload);
            Order order = new Order();
            order.setOfferId(message.get("data").get("offerId").asText());
            order.setBatterySize(message.get("data").get("batterySize").asText());
            order.setCustomerName(message.get("data").get("clientName").asText());
            order.setCustomerEmail(message.get("data").get("clientEmail").asText());


            String traceId = UUID.randomUUID().toString();
            InventoryFlowContext context = new InventoryFlowContext();
            context.setOfferId(order.getOfferId());
            context.setTraceId(traceId);
            context.setBatterySize(order.getBatterySize());
            context.setPartsAvailable(false);

            orderRepository.save(order);

            zeebe.newCreateInstanceCommand() //
                    .bpmnProcessId("inventory-service") //
                    .latestVersion() //
                    .variables(context.asMap()) //
                    .send().join();

        } catch (Exception e) {
            System.out.println("Error while starting the bpmn process: " + e.getMessage());
        }
    }

    @Transactional
    public void appointmentReceived(String messagePayload) throws Exception {
        System.out.println("Appointment received: " + messagePayload);

        AppointmentReply appointmentReply = new AppointmentReply(messagePayload);

        HashMap<String, String> newAppointment = new HashMap<>();
        newAppointment.put("appointmentDate",appointmentReply.getAppointmentDate());

        zeebe.newPublishMessageCommand() //
                .messageName("ClientAppointmentReceivedEvent")
                .correlationKey(appointmentReply.getOfferId())
                .variables(newAppointment)
                .send().join();

        System.out.println("Appointment date correlated for offer " + appointmentReply.getOfferId());

    }

    @Transactional
    public void partsReceived(String messagePayload) throws Exception {
        System.out.println("Parts received: " + messagePayload);

        PartsReceivedDto partsReceivedDto = new PartsReceivedDto(messagePayload);

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("partsAvailable",partsReceivedDto.getPartsConfirmed().toString());

        zeebe.newPublishMessageCommand() //
                .messageName("PartsReceivedEvent")
                .correlationKey(partsReceivedDto.getOfferId())
                .variables(newVariables)
                .send().join();

        System.out.println("Parts received correlated for offer " + partsReceivedDto.getOfferId());
    }

    @Transactional
    public void complementaryMaterialsReceived(String messagePayload) throws Exception {
        System.out.println("Complementary materials received: " + messagePayload);

        ComplementaryMaterialsReceivedDto complementaryMaterialsReceivedDto = new ComplementaryMaterialsReceivedDto(messagePayload);

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("complementaryMaterialsAvailable","true");

        zeebe.newPublishMessageCommand() //
                .messageName("ComplementaryMaterialsReceivedEvent")
                .correlationKey(complementaryMaterialsReceivedDto.getOfferId())
                .variables(newVariables)
                .send().join();

        System.out.println("Complementary materials received correlated for offer " + complementaryMaterialsReceivedDto.getOfferId());
    }

}