package mk.ru.orderorchestrator.services.order;

import mk.ru.orderorchestrator.web.requests.*;

import java.util.*;

public interface OrderService {
    void changeStatus(ChangeStatusOrderRequest request, UUID customerId, UUID orderId);

    void deliverOrder(AddDeliveryDateRequest request);
}
