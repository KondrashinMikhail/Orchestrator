package mk.ru.orderorchestrator.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusOrderRequest {
    private String status;
}
