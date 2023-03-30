package ch.unisg.ems.sales.delegate;

import ch.unisg.ems.sales.dto.CamundaOfferDto;
import ch.unisg.ems.sales.util.VariablesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OfferDelegate implements JavaDelegate {

    private final KafkaTemplate<String, CamundaOfferDto> kafkaTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Processing Offer {}", delegateExecution.getCurrentActivityId());
        CamundaOfferDto camundaOfferDto = VariablesUtil.buildCamudaOfferDto(delegateExecution.getProcessBusinessKey(), delegateExecution.getVariables());
        kafkaTemplate.send("service-task-message-topic", camundaOfferDto);

    }
}
