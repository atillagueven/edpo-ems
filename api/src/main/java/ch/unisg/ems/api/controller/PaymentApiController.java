package ch.unisg.ems.api.controller;

import ch.unisg.ems.api.dto.CamundaMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PaymentApiController {

    private final KafkaTemplate<String, CamundaMessageDto> kafkaTemplate;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        // get request to check webserver liveliness
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/start-payment-process")
    public void startMessageProcess(@RequestBody CamundaMessageDto camundaMessageDto){
        kafkaTemplate.send("payment-required-topic", camundaMessageDto);
    }

    @PostMapping("/payment-received")
    public void correlateIntermediatePaymentReceivedMessage(@RequestBody String correlationId){
        kafkaTemplate.send("payment-reveived-topic", CamundaMessageDto.builder().correlationId(correlationId).build());
    }
}
