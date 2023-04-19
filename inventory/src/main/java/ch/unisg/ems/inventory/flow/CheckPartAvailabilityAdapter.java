package ch.unisg.ems.inventory.flow;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CheckPartAvailabilityAdapter {

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @ZeebeWorker(type = "check-parts-availability")
    public void orderParts(JobClient client, ActivatedJob job) {
        System.out.println("Check availability");

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("partsAvailable","false");

        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();
    }
}
