package ch.unisg.ems.payment.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ch.unisg.ems.payment.dto.CamundaMessageDto;
import ch.unisg.ems.payment.util.VariablesUtil;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageDelegate implements JavaDelegate {

    private final KafkaTemplate<String, CamundaMessageDto> kafkaTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Executing task {}", delegateExecution.getCurrentActivityId());
        CamundaMessageDto camundaMessageDto = VariablesUtil.buildCamundaMessageDto(delegateExecution.getProcessBusinessKey(), delegateExecution.getVariables());
        kafkaTemplate.send("service-task-message-topic", camundaMessageDto);
    }
}
