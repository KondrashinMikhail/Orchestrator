package mk.ru.orderorchestrator.configurations.webclients;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.*;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rest.contract-service")
public class ContractWebClient {
    private String host;

    @Bean
    protected WebClient webClientContract() {
        return WebClient.builder()
                .baseUrl(host)
                .build();
    }
}
