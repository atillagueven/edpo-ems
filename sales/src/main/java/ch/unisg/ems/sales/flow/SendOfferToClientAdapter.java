package ch.unisg.ems.sales.flow;

import ch.unisg.ems.sales.flow.payload.SendOfferToClientCommandPayload;
import ch.unisg.ems.sales.messages.MessageSender;
import ch.unisg.ems.sales.messages.Message;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SendOfferToClientAdapter {

    @Autowired
    private MessageSender messageSender;


    @ZeebeWorker(type = "send-offer-to-client")
    public void sendOfferToClient(JobClient client, ActivatedJob job) {
        System.out.println("Sending offer to client: ");

        /*OrderFlowContext context = OrderFlowContext.fromMap(job.getVariablesAsMap());
        Offer offer = orderRepository.findById(context.getOrderId()).get();*/

        // generate an UUID for this communication
//        String correlationId = UUID.randomUUID().toString();
//
//        messageSender.send(new Message<SendOfferToClientCommandPayload>( //
//                "SendOfferToClientCommand", //
//                new SendOfferToClientCommandPayload() //
//        ));

        client.newCompleteCommand(job.getKey()).send().join();
    }
}
