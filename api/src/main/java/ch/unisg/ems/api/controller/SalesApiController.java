package ch.unisg.ems.api.controller;

import ch.unisg.ems.api.dto.CamundaMessageDto;
import ch.unisg.ems.api.dto.OfferAnswerDto;
import ch.unisg.ems.api.dto.OfferRequestDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class SalesApiController {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/sales/request-offer")
    public void startOfferProcess(@RequestBody OfferRequestDto offerRequestDto){
        System.out.println("New offer requested by " + offerRequestDto.getCustomerEmail());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(offerRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-sales", jsonMessage);
        record.headers().add("type", "OfferRequestedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

    @PostMapping("/sales/answer-offer")
    public void submitAnswer(@RequestBody OfferAnswerDto offerAnswerDto){
        System.out.println("Client answer received for: " + offerAnswerDto.getOfferId());


        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(offerAnswerDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-sales", jsonMessage);
        record.headers().add("type", "ClientAnswerReceivedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

}
