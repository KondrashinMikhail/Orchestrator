package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.contract.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.CONTRACT_ID_FIELD;

@Component
@AllArgsConstructor
public class DeleteContractDelegate implements JavaDelegate {
    private ContractService contractService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Delete contract Delegate");

        contractService.deleteContract((UUID) delegateExecution.getVariable(CONTRACT_ID_FIELD));

        System.err.println("    Deleted contract id: " + delegateExecution.getVariable(CONTRACT_ID_FIELD));
    }
}
