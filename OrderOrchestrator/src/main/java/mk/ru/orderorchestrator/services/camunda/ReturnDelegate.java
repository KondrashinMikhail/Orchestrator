package mk.ru.orderorchestrator.services.camunda;

import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

@Component
public class ReturnDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            System.err.println("Return Delegate");
            delegateExecution.getProcessEngineServices().getRuntimeService()
                    .createProcessInstanceModification(delegateExecution.getProcessInstanceId())
                    .startAfterActivity("CreatePaymentActivity")
                    .execute();

        } catch (Exception e) {
            throw new BpmnError("Error");
        }
    }
}
