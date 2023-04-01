package ch.unisg.ems.api.controller;

import ch.unisg.ems.api.dto.CamundaMessageDto;
import ch.unisg.ems.api.dto.OfferRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class SalesApiController {

    private final KafkaTemplate<String, OfferRequestDto> kafkaTemplate;

    @PostMapping("/request-offer")
    public void startOfferProcess(@RequestBody OfferRequestDto offerRequestDto){
        System.out.println("New offer requested by " + offerRequestDto.getEmail());

        kafkaTemplate.send("sales-service", offerRequestDto);
    }

}
