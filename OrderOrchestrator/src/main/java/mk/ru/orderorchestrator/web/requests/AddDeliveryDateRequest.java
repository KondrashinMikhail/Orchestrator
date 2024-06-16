package mk.ru.orderorchestrator.web.requests;

import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDeliveryDateRequest {
    private UUID orderId;
    private LocalDate deliveryDate;
}
