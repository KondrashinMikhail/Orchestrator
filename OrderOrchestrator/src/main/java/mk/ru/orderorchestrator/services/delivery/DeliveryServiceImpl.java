package mk.ru.orderorchestrator.services.delivery;

import lombok.*;
import mk.ru.orderorchestrator.configurations.rest.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.time.*;
import java.util.*;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final RestConfiguration restConfiguration;
    private final WebClient webClientDelivery;

    @Override
    public LocalDate createDelivery(CreateDeliveryRequest request) {
        return webClientDelivery
                .post()
                .uri(restConfiguration.getDeliveryService().getMethods().getCreateDelivery())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LocalDate.class)
                .block();
    }

    @Override
    public void cancelDelivery(UUID orderId) {
        webClientDelivery
                .delete()
                .uri(restConfiguration.getDeliveryService().getMethods().getCancelDelivery() + "/" + orderId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
