package mk.ru.orderorchestrator.configurations.webclients;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.*;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rest.payment-service")
public class PaymentWebClient {
    private String host;

    @Bean
    protected WebClient webClientPayment() {
        return WebClient.builder()
                .baseUrl(host)
                .build();
    }
}
