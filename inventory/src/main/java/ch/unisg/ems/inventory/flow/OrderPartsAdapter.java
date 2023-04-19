package ch.unisg.ems.inventory.flow;

import ch.unisg.ems.inventory.persistence.OrderRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class OrderPartsAdapter {

    @Qualifier("zeebeClientLifecycle")
    @Autowired
    private ZeebeClient zeebe;

    @ZeebeWorker(type = "order-parts")
    public void orderParts(JobClient client, ActivatedJob job) {
        System.out.println("Order Parts Adapter");
        //InventoryFlowContext context = InventoryFlowContext.fromMap(job.getVariablesAsMap());


        Integer consumption = 300;
        String emsSystem = "";

        if (consumption > 500) {
            emsSystem = "Premium";
        }
        else if (consumption > 200) {
            emsSystem = "Standard";
        }
        else {
            emsSystem = "Entry";
        }

        HashMap<String, String> newSystem = new HashMap<>();
        newSystem.put("emsSystemRecommendation",emsSystem);

        client.newCompleteCommand(job.getKey())
                .variables(newSystem)
                .send()
                .join();

    }
}