package ch.unisg.ems.payment.flow;


import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import io.camunda.zeebe.client.api.worker.JobWorker;

@Component
public class SendInvoiceAdapter {

    @ZeebeWorker(type = "send-invoice")
    public void handle(JobClient client, ActivatedJob job) {
        // generate an UUID for this communication
        String correlationId = UUID.randomUUID().toString();

        System.out.println("Access service Send Invoice");


        client.newCompleteCommand(job.getKey()) //
                .variables(Collections.singletonMap("CorrelationId_FetchGoods", correlationId)) //
                .send().join();
    }
}