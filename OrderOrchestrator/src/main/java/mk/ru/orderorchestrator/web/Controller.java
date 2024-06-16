package mk.ru.orderorchestrator.web;

import lombok.*;
import mk.ru.orderorchestrator.services.kafka.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.camunda.bpm.engine.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static mk.ru.orderorchestrator.utils.CamundaUtils.ACCOUNT_NUMBER_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.CUSTOMER_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.DELIVERY_ADDRESS_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.INN_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.TOTAL_PRICE_FIELD;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {
    private RuntimeService runtimeService;

    @PostMapping("/start")
    public String startProcess(@RequestBody CamundaProcessOrderRequest request) {
        return runtimeService.createProcessInstanceByKey("OrchestratorProcessKey")
                .businessKey(UUID.randomUUID().toString())
                .setVariable("error", false)
                .setVariable(ORDER_ID_FIELD, request.getOrderId())
                .setVariable(INN_FIELD, request.getInn())
                .setVariable(ACCOUNT_NUMBER_FIELD, request.getAccountNumber())
                .setVariable(CUSTOMER_ID_FIELD, request.getCustomerId())
                .setVariable(DELIVERY_ADDRESS_FIELD, request.getDeliveryAddress())
                .setVariable(TOTAL_PRICE_FIELD, request.getTotalPrice())
                .execute()
                .getBusinessKey();
    }

    @PostMapping("/key/{processId}")
    public void setKey(@PathVariable UUID processId) {
        runtimeService.createMessageCorrelation("СompilanceResult")
                .setVariable("СompilanceResult", "SUCCESS")
                .processInstanceBusinessKey(processId.toString())
                .correlate();
    }
}
