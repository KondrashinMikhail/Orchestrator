package mk.ru.orderorchestrator.services.delivery;

import mk.ru.orderorchestrator.web.requests.*;

import java.time.*;
import java.util.*;

public interface DeliveryService {
    LocalDate createDelivery(CreateDeliveryRequest request);

    void cancelDelivery(UUID orderId);
}
