package mk.ru.orderorchestrator.web.requests;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private UUID orderId;
    private Double totalPrice;
    private Long accountNumber;
    private Long inn;
}
