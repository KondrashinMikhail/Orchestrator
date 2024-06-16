package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.payment.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.ACCOUNT_NUMBER_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.INN_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.TOTAL_PRICE_FIELD;

@Component
@AllArgsConstructor
public class CreatePaymentDelegate implements JavaDelegate {
    private PaymentService paymentService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create payment Delegate");

        String response = paymentService.pay(
                PaymentRequest.builder()
                        .orderId((UUID) delegateExecution.getVariable(ORDER_ID_FIELD))
                        .totalPrice((Double) delegateExecution.getVariable(TOTAL_PRICE_FIELD))
                        .accountNumber((Long) delegateExecution.getVariable(ACCOUNT_NUMBER_FIELD))
                        .inn((Long) delegateExecution.getVariable(INN_FIELD))
                        .build());

        if (Objects.equals(response, "FAIL")) throw new BpmnError("error");
    }
}
