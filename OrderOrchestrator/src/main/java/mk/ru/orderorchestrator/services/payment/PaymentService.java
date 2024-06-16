package mk.ru.orderorchestrator.services.payment;

import mk.ru.orderorchestrator.web.requests.*;

public interface PaymentService {
    String pay(PaymentRequest request);
}
