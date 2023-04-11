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

import java.util.HashMap;

@Component
public class CreateOfferAdapter {

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @Autowired
    private OfferRepository offerRepository;

    @ZeebeWorker(type = "create-offer")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access service CreateOffer");
        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());

        String recommendedBatterySize = "";
        Integer consumption = context.getLoadProfile();

        if (consumption > 500) {
            recommendedBatterySize = "Large";
        } else if (consumption > 200) {
            recommendedBatterySize = "Medium";
        } else {
            recommendedBatterySize = "Small";
        }

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("batterySizeRecomendation",recommendedBatterySize);


        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();
    }
}
