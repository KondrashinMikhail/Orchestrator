package mk.ru.orderorchestrator.web.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilanceRequest {
    private String customerId;
    private String inn;
    private String processId;
}
