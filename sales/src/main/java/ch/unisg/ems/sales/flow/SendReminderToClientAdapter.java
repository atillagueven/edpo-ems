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

import java.util.HashMap;

public class SendReminderToClientAdapter {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OfferRepository offerRepository;


    @ZeebeWorker(type = "send-reminder-to-client")
    public void sendReminderToClient(JobClient client, ActivatedJob job) {
        System.out.println("Sending Reminder to client: ");

        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Offer offer = offerRepository.findById(context.getOfferId()).get();

        SendOfferToClientCommandPayload payload = new SendOfferToClientCommandPayload();
        payload.setOfferId(context.getOfferId());
        payload.setClientName(offer.getCustomerName());
        payload.setClientEmail(offer.getCustomerEmail());
        payload.setOfferMessage(context.getOfferMessage());

        System.out.println("Sending offer to client: " + payload);

        messageSender.send(new Message<SendOfferToClientCommandPayload>(
                "SendOfferToClientCommand",
                payload
        ), "ems-notification");

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("reminderSent","true");

        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();
    }
}
