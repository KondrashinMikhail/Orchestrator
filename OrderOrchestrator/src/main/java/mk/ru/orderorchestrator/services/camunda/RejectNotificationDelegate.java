package mk.ru.orderorchestrator.services.camunda;

import lombok.*;
import mk.ru.orderorchestrator.services.notification.*;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.*;

@Component
@AllArgsConstructor
public class RejectNotificationDelegate implements JavaDelegate {
    private NotificationService notificationService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.err.println("Reject notification Delegate");

        notificationService.rejectNotification(delegateExecution.getProcessBusinessKey());
    }
}
