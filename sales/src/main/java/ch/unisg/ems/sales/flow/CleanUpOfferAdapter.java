package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.domain.Offer;
import ch.unisg.ems.sales.persistence.OfferRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class CleanUpOfferAdapter {

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @Autowired
    private OfferRepository offerRepository;

    @ZeebeWorker(type = "clean-up-offer")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access CleanUpOfferAdapter");
        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());

        Offer offer = offerRepository.findById(context.getOfferId()).get();
        offer.setOfferStatus("rejected");
        offerRepository.save(offer);

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}
