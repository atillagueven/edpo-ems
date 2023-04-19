package ch.unisg.ems.inventory.flow;

import ch.unisg.ems.inventory.flow.payload.SendAssemblyInfoToClientCommandPayload;
import ch.unisg.ems.inventory.messages.Message;
import ch.unisg.ems.inventory.messages.MessageSender;
import ch.unisg.ems.inventory.persistence.OrderRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendAssemblyInfoToClientAdapter {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OrderRepository orderRepository;


    @ZeebeWorker(type = "send-assembly-info-to-client")
    public void orderParts(JobClient client, ActivatedJob job) {
        System.out.println("Sending assembly information to client: ");

        SendAssemblyInfoToClientCommandPayload payload = new SendAssemblyInfoToClientCommandPayload();
        payload.setOfferId("123456789");
        payload.setClientName("Atilla Güven");
        payload.setClientEmail("atilla.gueven@student.unisg.ch");
        payload.setOfferMessage("test");
        payload.setEmsSystemRecommendation("Standard");

        System.out.println("Sending Assembly Information to client: " + payload);

        messageSender.send(new Message<SendAssemblyInfoToClientCommandPayload>(
                "SendAssemblyInfoToClientCommand",
                payload
        ), "ems-notification");

        client.newCompleteCommand(job.getKey()).send().join();
    }

}
