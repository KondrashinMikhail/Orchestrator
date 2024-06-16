package mk.ru.orderorchestrator.configurations.webclients;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.*;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rest.order-service")
public class OrderWebClient {
    private String host;

    @Bean
    protected WebClient webClientOrder() {
        return WebClient.builder()
                .baseUrl(host)
                .build();
    }
}
