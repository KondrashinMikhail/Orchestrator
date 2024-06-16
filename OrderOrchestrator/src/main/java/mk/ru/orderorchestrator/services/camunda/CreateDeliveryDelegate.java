package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.delivery.*;
import mk.ru.orderorchestrator.services.order.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Component
@AllArgsConstructor
public class CreateDeliveryDelegate implements JavaDelegate {
    private DeliveryService deliveryService;
    private OrderService orderService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create delivery Delegate");

        LocalDate date = deliveryService.createDelivery(CreateDeliveryRequest.builder()
                .orderId((UUID) delegateExecution.getVariable("orderId"))
                .deliveryAddress((String) delegateExecution.getVariable("deliveryAddress"))
                .build());

        delegateExecution.setVariable("deliveryDate", date);

        orderService.deliverOrder(AddDeliveryDateRequest.builder()
                .orderId((UUID) delegateExecution.getVariable("orderId"))
                .deliveryDate(date)
                .build());

        System.err.println("    Delivery date: " + date);
    }
}
