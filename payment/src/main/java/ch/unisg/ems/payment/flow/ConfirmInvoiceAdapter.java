package ch.unisg.ems.payment.flow;

import ch.unisg.ems.payment.domain.Invoice;
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
public class ConfirmInvoiceAdapter {
    @Autowired
    private MessageSender messageSender;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @ZeebeWorker(type = "confirm-invoice")
    public void handle(JobClient client, ActivatedJob job) {
        // generate a UUID for this communication
        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Invoice invoice = invoiceRepository.findById(context.getInvoiceId()).get();

        invoice.setPaid(true);

        invoiceRepository.save(invoice);

        HashMap<String, String> newVariables = new HashMap<>();
        newVariables.put("invoiceIsPaid", "true");

        System.out.println("Invoice is confirmed as payed for offer: " + invoice.getOfferId());

        client.newCompleteCommand(job.getKey())
                .variables(newVariables)
                .send()
                .join();
    }
}
