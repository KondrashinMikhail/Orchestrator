package mk.ru.orderorchestrator.configurations.rest;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@ConfigurationProperties(prefix = "rest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestConfiguration {
    private RestServiceInfo contractService;
    private RestServiceInfo deliveryService;
    private RestServiceInfo paymentService;
    private RestServiceInfo notificationService;
    private RestServiceInfo orderService;
}
