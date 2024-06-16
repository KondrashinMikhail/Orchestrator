package mk.ru.orderorchestrator.services.notification;

import lombok.*;
import mk.ru.orderorchestrator.configurations.rest.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final RestConfiguration restConfiguration;
    private final WebClient webClientNotification;

    @Override
    public void rejectNotification(String processId) {
        webClientNotification
                .post()
                .uri(restConfiguration.getNotificationService().getMethods().getRejectionNotification() + "?processId=" + processId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(logins)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
