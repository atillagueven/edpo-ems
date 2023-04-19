package ch.unisg.ems.api.controller;


import ch.unisg.ems.api.dto.ConfirmComplementaryMaterialsDto;
import ch.unisg.ems.api.dto.ConfirmPartsDto;
import ch.unisg.ems.api.dto.OrderRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class InventoryApiController {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/inventory/appointment-reply")
    public void startAppointmentProcess(@RequestBody OrderRequestDto orderRequestDto){
        System.out.println("New Appointment requested by " + orderRequestDto.getAppointmentDate());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(orderRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-inventory", jsonMessage);
        record.headers().add("type", "ClientAppointmentReceivedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

    @PostMapping("/inventory/confirmParts")
    public void confirmParts(@RequestBody ConfirmPartsDto confirmPartsDto){
        System.out.println("Parts confirmed for " + confirmPartsDto.getPartsConfirmed());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(confirmPartsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-inventory", jsonMessage);
        record.headers().add("type", "PartsReceivedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

    @PostMapping("/inventory/confirmComplementaryMaterials")
    public void confirmComplementaryMaterials(@RequestBody ConfirmComplementaryMaterialsDto confirmComplementaryMaterialsDto){
        System.out.println("Complementary Materials confirmed for " + confirmComplementaryMaterialsDto.getComplementaryMaterialsConfirmed());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(confirmComplementaryMaterialsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-inventory", jsonMessage);
        record.headers().add("type", "ComplementaryMaterialsReceivedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

}
