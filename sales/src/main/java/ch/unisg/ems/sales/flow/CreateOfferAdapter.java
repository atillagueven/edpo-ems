package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.domain.Offer;
import ch.unisg.ems.sales.persistence.OfferRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
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
        Offer offer = offerRepository.findById(context.getOfferId()).get();

        String recommendedBatterySize = "";
        int consumption = context.getLoadProfileConsumption();
        int production = context.getLoadProfileProduction();

        // Added error for demo purposes, in a real application this would be handled differently
        if(consumption < 0) {
            System.out.println("Battery size could not be calculated");
            throw new ZeebeBpmnError("Error_BatteryCalculationError", "Battery size could not be calculated");
        }

        int energySurplus = production - consumption;

        if (energySurplus > 500) {
            recommendedBatterySize = "large";
        } else if (energySurplus > 200) {
            recommendedBatterySize = "medium";
        } else {
            recommendedBatterySize = "small";
        }

        offer.setBatterySize(recommendedBatterySize);
        offerRepository.save(offer);

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("batterySizeRecommendation",recommendedBatterySize);

        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();
    }
}
