package ch.unisg.ems.api.controller;

import ch.unisg.ems.api.dto.CamundaMessageDto;
import ch.unisg.ems.api.dto.OfferPaymentDto;
import ch.unisg.ems.api.dto.PaymentDto;
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
public class PaymentApiController {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("payment/hello")
    public ResponseEntity<String> hello() {
        // get request to check webserver liveliness
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/payment/start-process")
    public void startMessageProcess(@RequestBody PaymentDto paymentDto){
        System.out.println("Payment process started for offer: " + paymentDto.getOfferId());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(paymentDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("ems-inventory", jsonMessage);
        record.headers().add("type", "InstallationCompleteEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }

    @PostMapping("/payment/payment-received")
    public void paymentReceived(@RequestBody OfferPaymentDto offerPaymentDto){
        System.out.println("Client answer received for: " + offerPaymentDto.getInvoiceId());

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(offerPaymentDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // wrap into a proper message for Kafka including a header
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("payment-service", jsonMessage);
        record.headers().add("type", "PaymentReceivedEvent".getBytes());

        // and send it
        kafkaTemplate.send(record);
    }
}
