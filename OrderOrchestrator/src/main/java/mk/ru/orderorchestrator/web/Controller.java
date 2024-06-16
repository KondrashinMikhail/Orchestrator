package mk.ru.orderorchestrator.web;

import lombok.*;
import mk.ru.orderorchestrator.services.kafka.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.camunda.bpm.engine.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {
    private CustomKafkaProducer kafkaProducer;
    private RuntimeService runtimeService;

    @PostMapping("/send")
    public void sendMessage(){
        kafkaProducer.sendMessage("test");
    }

    @PostMapping("/start")
    public String startProcess(@RequestBody CamundaProcessOrderRequest request) {
        return runtimeService.createProcessInstanceByKey("OrchestratorProcessKey")
                .businessKey(UUID.randomUUID().toString())
                .setVariable("error", false)
                .setVariable("orderId", request.getOrderId())
                .setVariable("inn", request.getInn())
                .setVariable("accountNumber", request.getAccountNumber())
                .setVariable("customerId", request.getCustomerId())
                .setVariable("deliveryAddress", request.getDeliveryAddress())
                .setVariable("totalPrice", request.getTotalPrice())
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
