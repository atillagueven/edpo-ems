package ch.unisg.ems.inventory.flow;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

public class SendAssemblyInfoToClientAdapter {

    @ZeebeWorker(type = "send-assembly-info-to-client")
    public void orderParts(JobClient client, ActivatedJob job) {
        //TODO implement logic

        client.newCompleteCommand(job.getKey()).send().join();
    }

    ;

}
