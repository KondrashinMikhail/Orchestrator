package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.order.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@AllArgsConstructor
public class FinalStatusDelegate implements JavaDelegate {
    private OrderService orderService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Final status Delegate");

        String status = (Boolean) delegateExecution.getVariable("error") ? "CANCELLED" : "CONFIRMED";

        orderService.changeStatus(
                ChangeStatusOrderRequest.builder().status(status).build(),
                (UUID) delegateExecution.getVariable("customerId"),
                (UUID) delegateExecution.getVariable("orderId"));

        System.err.println("    Set status " + status);
    }
}
