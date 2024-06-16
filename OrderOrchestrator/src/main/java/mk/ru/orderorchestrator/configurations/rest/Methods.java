package mk.ru.orderorchestrator.configurations.rest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Methods {
    private String createContract;
    private String deleteContract;
    private String createDelivery;
    private String cancelDelivery;
    private String pay;
    private String rejectionNotification;
    private String rejectOrder;
    private String addDelivery;
    private String updateStatus;
}
