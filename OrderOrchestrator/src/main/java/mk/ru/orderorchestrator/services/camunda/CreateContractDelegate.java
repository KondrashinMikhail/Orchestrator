package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.contract.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@AllArgsConstructor
public class CreateContractDelegate implements JavaDelegate {
    private ContractService contractService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create contract Delegate");

        UUID contractId = contractService.createContract(
                (Long) delegateExecution.getVariable("inn"),
                (Long) delegateExecution.getVariable("accountNumber"));

        System.err.println("    Created contract id: " + contractId);

        delegateExecution.setVariable("contractId", contractId);
    }
}
