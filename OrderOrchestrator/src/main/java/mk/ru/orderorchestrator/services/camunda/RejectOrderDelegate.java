package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.order.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@AllArgsConstructor
public class RejectOrderDelegate implements JavaDelegate {
    private OrderService orderService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Reject order Delegate");

        orderService.changeStatus(
                ChangeStatusOrderRequest.builder().status("REJECTED").build(),
                (UUID) delegateExecution.getVariable("customerId"),
                (UUID) delegateExecution.getVariable("orderId"));
    }
}
