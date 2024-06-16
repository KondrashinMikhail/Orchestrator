package mk.ru.orderorchestrator.services.kafka;

import com.fasterxml.jackson.databind.*;
import lombok.*;
import mk.ru.orderorchestrator.web.responses.*;
import org.camunda.bpm.engine.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class CustomKafkaListener {
    private RuntimeService runtimeService;

    @KafkaListener(topics = "listener_topic", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final KafkaResponse response = objectMapper.readValue(message, KafkaResponse.class);

            System.err.println("Message received: " + message);

            runtimeService.createMessageCorrelation("СompilanceResult")
                    .setVariable("СompilanceResult", message)
                    .processInstanceBusinessKey(response.getProcessId())
                    .correlate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
