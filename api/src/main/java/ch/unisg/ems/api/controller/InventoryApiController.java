package ch.unisg.ems.api.controller;


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

}
