package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.domain.Offer;
import ch.unisg.ems.sales.flow.payload.OfferPlacedEventPayload;
import ch.unisg.ems.sales.messages.Message;
import ch.unisg.ems.sales.messages.MessageSender;
import ch.unisg.ems.sales.persistence.OfferRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferPlacedAdapter {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OfferRepository offerRepository;

    @ZeebeWorker(type = "order-placed")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access service OfferPlaced");

        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Offer offer = offerRepository.findById(context.getOfferId()).get();

        OfferPlacedEventPayload payload = new OfferPlacedEventPayload();
        payload.setOfferId(context.getOfferId());
        payload.setClientName(offer.getCustomerName());
        payload.setClientEmail(offer.getCustomerEmail());
        payload.setBatterySize(offer.getBatterySize());

        System.out.println("Publish order placed event: " + payload);

        messageSender.send(new Message<OfferPlacedEventPayload>(
                "OrderPlacedEvent",
                payload
        ),"ems-sales");

        client.newCompleteCommand(job.getKey()).send().join();
    }
}
