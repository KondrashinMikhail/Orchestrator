package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.payment.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@AllArgsConstructor
public class CreatePaymentDelegate implements JavaDelegate {
    private PaymentService paymentService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create payment Delegate");

        String response = paymentService.pay(
                PaymentRequest.builder()
                        .orderId((UUID) delegateExecution.getVariable("orderId"))
                        .totalPrice((Double) delegateExecution.getVariable("totalPrice"))
                        .accountNumber((Long) delegateExecution.getVariable("accountNumber"))
                        .inn((Long) delegateExecution.getVariable("inn"))
                        .build());

        if (Objects.equals(response, "FAIL")) throw new BpmnError("error");
    }
}
