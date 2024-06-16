package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.delivery.*;
import mk.ru.orderorchestrator.services.order.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.DELIVERY_ADDRESS_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.DELIVERY_DATE_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;

@Component
@AllArgsConstructor
public class CreateDeliveryDelegate implements JavaDelegate {
    private DeliveryService deliveryService;
    private OrderService orderService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Create delivery Delegate");

        LocalDate date = deliveryService.createDelivery(CreateDeliveryRequest.builder()
                .orderId((UUID) delegateExecution.getVariable(ORDER_ID_FIELD))
                .deliveryAddress((String) delegateExecution.getVariable(DELIVERY_ADDRESS_FIELD))
                .build());

        delegateExecution.setVariable(DELIVERY_DATE_FIELD, date);

        orderService.deliverOrder(AddDeliveryDateRequest.builder()
                .orderId((UUID) delegateExecution.getVariable(ORDER_ID_FIELD))
                .deliveryDate(date)
                .build());

        System.err.println("    Delivery date: " + date);
    }
}
