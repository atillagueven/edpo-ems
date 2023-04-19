package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.domain.Offer;
import ch.unisg.ems.sales.flow.payload.SendOfferToClientCommandPayload;
import ch.unisg.ems.sales.messages.Message;
import ch.unisg.ems.sales.messages.MessageSender;
import ch.unisg.ems.sales.persistence.OfferRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationToEngineerAdapter {



    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OfferRepository offerRepository;


    @ZeebeWorker(type = "send-notification-to-engineer")
    public void sendOfferToClient(JobClient client, ActivatedJob job) {
        System.out.println("Sending offer to engineer: ");

       messageSender.send(new Message<String>(
                "SendNotificationToEngineerCommand",
                "Offer needs to checked by engineer"
        ), "ems-notification");

        client.newCompleteCommand(job.getKey()).send().join();
    }
}
