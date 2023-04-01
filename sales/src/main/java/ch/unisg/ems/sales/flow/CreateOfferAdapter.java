package ch.unisg.ems.sales.flow;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.stereotype.Component;

@Component
public class CreateOfferAdapter {

    @ZeebeWorker(type = "create-offer")
    public void handle(JobClient client, ActivatedJob job) {
        System.out.println("Access service CreateOffer");
        /*OrderFlowContext context = OrderFlowContext.fromMap(job.getVariablesAsMap());

        messageSender.send( //
                new Message<OrderCompletedEventPayload>( //
                        "OfferCreatedEvent", //
                        context.getTraceId(), //
                        new OrderCompletedEventPayload() //
                                .setOrderId(context.getOrderId())));

*/
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
