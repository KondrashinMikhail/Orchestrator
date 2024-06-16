package mk.ru.orderorchestrator.web.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaResponse {
    private String processId;
    private String status;
}
