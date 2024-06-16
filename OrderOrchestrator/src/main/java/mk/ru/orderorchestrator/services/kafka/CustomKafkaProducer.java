package mk.ru.orderorchestrator.services.kafka;

import lombok.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CustomKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private String TOPIC_NAME = "producer_topic";

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
        System.err.println("Message " + message + " has been successfully sent to the topic: " + TOPIC_NAME);
    }
}
