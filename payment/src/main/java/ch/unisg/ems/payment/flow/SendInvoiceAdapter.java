package ch.unisg.ems.payment.flow;


import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.unisg.ems.payment.domain.Invoice;
import ch.unisg.ems.payment.flow.payload.SendInvoiceToClientCommandPayload;
import ch.unisg.ems.payment.flow.payload.SendReminderToClientCommandPayload;
import ch.unisg.ems.payment.messages.Message;
import ch.unisg.ems.payment.messages.MessageSender;
import ch.unisg.ems.payment.persistence.InvoiceRepository;
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

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @ZeebeWorker(type = "send-invoice")
    public void handle(JobClient client, ActivatedJob job) {
        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Invoice invoice = invoiceRepository.findById(context.getInvoiceId()).get();

        SendInvoiceToClientCommandPayload payload = new SendInvoiceToClientCommandPayload();
        payload.setOfferId(invoice.getOfferId());
        payload.setInvoiceId(invoice.getId());
        payload.setClientEmail(invoice.getClientEmail());
        payload.setMessage("Pay invoice with given amount");
        payload.setAmount(String.valueOf(invoice.getAmount()));

        System.out.println("Sending invoice to client: " + payload);

        messageSender.send(new Message<SendInvoiceToClientCommandPayload>(
                "SendInvoiceToClientCommand",
                payload
        ), "ems-notification");


        System.out.println("Invoice is sent for offer: " + invoice.getOfferId());

        client.newCompleteCommand(job.getKey())
                .send()
                .join();
    }
}