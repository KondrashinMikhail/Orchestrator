package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.contract.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@AllArgsConstructor
public class DeleteContractDelegate implements JavaDelegate {
    private ContractService contractService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Delete contract Delegate");

        contractService.deleteContract((UUID) delegateExecution.getVariable("contractId"));

        System.err.println("    Deleted contract id: " + delegateExecution.getVariable("contractId"));
    }
}
