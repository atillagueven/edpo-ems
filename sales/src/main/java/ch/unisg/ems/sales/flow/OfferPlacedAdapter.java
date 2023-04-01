package ch.unisg.ems.sales.flow;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.stereotype.Component;

@Component
public class OfferPlacedAdapter {

    @ZeebeWorker(type = "order-placed")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access service OfferPlaced");

        client.newCompleteCommand(job.getKey()).send().join();
    }
}
