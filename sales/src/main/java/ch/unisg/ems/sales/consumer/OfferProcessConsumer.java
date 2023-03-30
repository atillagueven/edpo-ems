package ch.unisg.ems.sales.consumer;

import ch.unisg.ems.sales.dto.CamundaOfferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OfferProcessConsumer {

    private final RuntimeService runtimeService;
    private final OfferService offerService;
    private final static String MESSAGE_REQUEST_OFFER = "MessageRequestOffer";
    private final static String MESSAGE_ACCEPT_OFFER = "MessageAcceptedOffer";

    @KafkaListener(topics = "request-offer-message-topic")
    public void startSalesProcess(CamundaOfferDto camundaOfferDto){
        offerService.correlateMessage(camundaOfferDto, MESSAGE_REQUEST_OFFER);
    }

    @KafkaListener(topics = "offer-accepted-message-topic")
    public void listen(CamundaOfferDto camundaOfferDto){
       offerService.correlateMessage(camundaOfferDto, MESSAGE_ACCEPT_OFFER);
    }
}
