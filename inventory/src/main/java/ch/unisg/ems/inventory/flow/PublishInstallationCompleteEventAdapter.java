package ch.unisg.ems.inventory.flow;

import ch.unisg.ems.inventory.domain.Order;
import ch.unisg.ems.inventory.flow.payload.InstallationCompleteEventPayload;
import ch.unisg.ems.inventory.messages.Message;
import ch.unisg.ems.inventory.messages.MessageSender;
import ch.unisg.ems.inventory.persistence.OrderRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishInstallationCompleteEventAdapter {


    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OrderRepository orderRepository;

    @ZeebeWorker(type = "installation-complete")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access service OfferPlaced");

        InventoryFlowContext context = InventoryFlowContext.fromMap(job.getVariablesAsMap());
        Order order = orderRepository.findById(context.getOfferId()).get();

        InstallationCompleteEventPayload payload = new InstallationCompleteEventPayload();
        payload.setOfferId(context.getOfferId());
        payload.setClientName(order.getCustomerName());
        payload.setClientEmail(order.getCustomerEmail());
        payload.setBatterySize(order.getBatterySize());

        System.out.println("Publish installation-complete event: " + payload);

        messageSender.send(new Message<InstallationCompleteEventPayload>(
                "InstallationCompleteEvent",
                payload
        ));

        client.newCompleteCommand(job.getKey()).send().join();
    }


}
