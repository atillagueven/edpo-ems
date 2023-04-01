package ch.unisg.ems.sales.flow;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.stereotype.Component;

@Component
public class SendOfferToClientAdapter {

    @ZeebeWorker(type = "send-offer-to-client")
    public void sendOfferToClient(JobClient client, ActivatedJob job) {
        System.out.println("Sending offer to client: ");
        client.newCompleteCommand(job.getKey()).send().join();

    }
}
