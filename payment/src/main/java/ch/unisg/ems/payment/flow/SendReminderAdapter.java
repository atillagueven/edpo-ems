package ch.unisg.ems.payment.flow;

import ch.unisg.ems.payment.domain.Invoice;
import ch.unisg.ems.payment.flow.payload.SendReminderToClientCommandPayload;
import ch.unisg.ems.payment.messages.Message;
import ch.unisg.ems.payment.messages.MessageSender;
import ch.unisg.ems.payment.persistence.InvoiceRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SendReminderAdapter {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @ZeebeWorker(type = "send-reminder")
    public void handle(JobClient client, ActivatedJob job) {

        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
//        Invoice invoice = invoiceRepository.findById(context.getInvoiceId()).get();

        SendReminderToClientCommandPayload payload = new SendReminderToClientCommandPayload();
        payload.setOfferId(context.getOfferId());
        payload.setInvoiceId(context.getInvoiceId());
        payload.setClientEmail(context.getClientEmail());
        payload.setMessage("Reminder to pay invoice");

        System.out.println("Sending reminder to client: " + payload);

        messageSender.send(new Message<SendReminderToClientCommandPayload>(
                "SendInvoiceReminderToClientCommand",
                payload
        ), "ems-notification");

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("reminderSent","true");

        System.out.println("Invoice reminder is sent");

        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();

    }
}
