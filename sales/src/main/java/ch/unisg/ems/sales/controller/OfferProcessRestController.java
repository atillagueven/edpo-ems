package ch.unisg.ems.sales.controller;

import ch.unisg.ems.sales.dto.CamundaOfferDto;
import lombok.RequiredArgsConstructor;
import ch.unisg.ems.sales.dto.OfferDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer-process")
@RequiredArgsConstructor
public class OfferProcessRestController {

    private final KafkaTemplate<String, CamundaOfferDto> kafkaOfferTemplate;

    @PostMapping("/requestOffer")
    public void requestOffer(@RequestBody CamundaOfferDto camundaOfferDto){
        kafkaOfferTemplate.send("request-offer-message-topic", camundaOfferDto);
    }

    @PostMapping("/offerAccepted")
    public void offerAccepted(@RequestBody String correlationId){
        kafkaOfferTemplate.send("offer-accepted-message-topic", CamundaOfferDto.builder().correlationId(correlationId).build());
    }
}
