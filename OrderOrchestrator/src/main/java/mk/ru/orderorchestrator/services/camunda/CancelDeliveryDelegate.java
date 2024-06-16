package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.delivery.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;

@Component
@AllArgsConstructor
public class CancelDeliveryDelegate implements JavaDelegate {
    private DeliveryService deliveryService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Cancel delivery Delegate");

        deliveryService.cancelDelivery((UUID) delegateExecution.getVariable(ORDER_ID_FIELD));
    }
}
