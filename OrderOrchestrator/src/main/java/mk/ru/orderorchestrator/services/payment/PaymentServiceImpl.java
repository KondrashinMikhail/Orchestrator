package mk.ru.orderorchestrator.services.payment;

import lombok.*;
import mk.ru.orderorchestrator.configurations.rest.*;
import mk.ru.orderorchestrator.web.requests.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RestConfiguration restConfiguration;
    private final WebClient webClientPayment;

    @Override
    public String pay(PaymentRequest request) {
        return webClientPayment
                .post()
                .uri(restConfiguration.getPaymentService().getMethods().getPay())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
