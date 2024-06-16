package mk.ru.orderorchestrator.services.camunda;

import com.fasterxml.jackson.databind.*;
import lombok.*;
import mk.ru.orderorchestrator.services.kafka.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.INN_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;

@Component
@AllArgsConstructor
public class CompilanceDelegate implements JavaDelegate {
    private CustomKafkaProducer kafkaProducer;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Compliance Delegate");

        ObjectMapper objectMapper = new ObjectMapper();

        String message = objectMapper.writeValueAsString(
                CompilanceRequest.builder()
                        .customerId(delegateExecution.getVariable(ORDER_ID_FIELD).toString())
                        .inn(((Long) delegateExecution.getVariable(INN_FIELD)).toString())
                        .processId(delegateExecution.getProcessBusinessKey())
                        .build());

        System.err.println("    Message: " + message);

        kafkaProducer.sendMessage(message);

    }
}
