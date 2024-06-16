package mk.ru.orderorchestrator.services.order;

import lombok.*;
import mk.ru.orderorchestrator.configurations.rest.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private WebClient webClientOrder;
    private RestConfiguration restConfiguration;

    @Override
    public void changeStatus(ChangeStatusOrderRequest request, UUID customerId, UUID orderId) {
        webClientOrder.patch()
                .uri(restConfiguration.getOrderService().getMethods().getUpdateStatus().replace("{orderId}", orderId.toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .header("customerId", customerId.toString())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void deliverOrder(AddDeliveryDateRequest request) {
        webClientOrder.patch()
                .uri(restConfiguration.getOrderService().getMethods().getAddDelivery())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
