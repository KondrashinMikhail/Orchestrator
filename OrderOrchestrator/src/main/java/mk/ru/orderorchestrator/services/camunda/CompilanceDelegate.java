package mk.ru.orderorchestrator.services.camunda;

import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

@Component
public class CompilanceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Compliance Delegate");
    }
}
