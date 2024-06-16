package mk.ru.orderorchestrator.services.contract;

import java.util.*;

public interface ContractService {
    UUID createContract(Long inn, Long accountNumber);

    void deleteContract(UUID contractId);
}
