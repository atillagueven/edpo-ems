package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.domain.Offer;
import ch.unisg.ems.sales.flow.payload.SendEmailCommandPayload;
import ch.unisg.ems.sales.messages.MessageSender;
import ch.unisg.ems.sales.messages.Message;
import ch.unisg.ems.sales.persistence.OfferRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendOfferToClientAdapter {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OfferRepository offerRepository;


    @ZeebeWorker(type = "send-offer-to-client")
    public void sendOfferToClient(JobClient client, ActivatedJob job) {
        System.out.println("Sending offer to client: ");

        OfferFlowContext context = OfferFlowContext.fromMap(job.getVariablesAsMap());
        Offer offer = offerRepository.findById(context.getOfferId()).get();

        String emailContent = "Dear " + offer.getCustomerName() + ",\n\n"
                + "REMINDER: \n"
                + context.getOfferMessage() + "\n\n"
                + "Please click on the following link to accept the offer:\n"
                + "http://localhost:3000/sales/offer-reply?id=" + context.getOfferId() + "\n\n"
                + "Best regards,\n" + "EMS Team";

        SendEmailCommandPayload payload = new SendEmailCommandPayload();
        payload.setClientEmail(offer.getCustomerEmail());
        payload.setEmailContent(emailContent);

        System.out.println("Sending offer to client: " + payload);

        messageSender.send(new Message<SendEmailCommandPayload>(
                "SendOfferToClientCommand",
                payload
        ), "ems-notification");

        client.newCompleteCommand(job.getKey()).send().join();
    }
}
