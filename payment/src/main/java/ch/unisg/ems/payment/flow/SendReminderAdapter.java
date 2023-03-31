package ch.unisg.ems.payment.flow;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;
@Component
public class SendReminderAdapter {
    @ZeebeWorker(type = "SendReminder")
    public void handle(JobClient client, ActivatedJob job) {
        // generate an UUID for this communication
        String correlationId = UUID.randomUUID().toString();

        System.out.println("Access service Send Reminder");


        client.newCompleteCommand(job.getKey()) //
                .variables(Collections.singletonMap("CorrelationId_FetchGoods", correlationId)) //
                .send().join();
    }
}
