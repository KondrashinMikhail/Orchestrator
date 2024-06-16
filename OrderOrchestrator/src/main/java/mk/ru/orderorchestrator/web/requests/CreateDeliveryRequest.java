package mk.ru.orderorchestrator.web.requests;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeliveryRequest {
    private UUID orderId;
    private String deliveryAddress;
}
