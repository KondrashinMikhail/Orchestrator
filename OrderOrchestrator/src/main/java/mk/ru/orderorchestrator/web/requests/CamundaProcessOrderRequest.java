package mk.ru.orderorchestrator.web.requests;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamundaProcessOrderRequest {
    private UUID orderId;
    private UUID customerId;
    private String deliveryAddress;
    private Long inn;
    private Long accountNumber;
    private Double totalPrice;
}
