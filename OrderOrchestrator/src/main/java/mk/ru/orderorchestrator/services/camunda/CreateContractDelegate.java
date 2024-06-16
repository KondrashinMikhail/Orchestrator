package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.contract.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.ACCOUNT_NUMBER_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.CONTRACT_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.INN_FIELD;

@Component
@AllArgsConstructor
public class CreateContractDelegate implements JavaDelegate {
    private ContractService contractService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create contract Delegate");

        UUID contractId = contractService.createContract(
                (Long) delegateExecution.getVariable(INN_FIELD),
                (Long) delegateExecution.getVariable(ACCOUNT_NUMBER_FIELD));

        System.err.println("    Created contract id: " + contractId);

        delegateExecution.setVariable(CONTRACT_ID_FIELD, contractId);
    }
}
