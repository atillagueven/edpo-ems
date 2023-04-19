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
public class CompensateOfferCreation {

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @Autowired
    private OfferRepository offerRepository;

    @ZeebeWorker(type = "compensate-offer-creation")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access CompensateOfferCreation");
        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Offer offer = offerRepository.findById(context.getOfferId()).get();

        offerRepository.delete(offer);

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}
