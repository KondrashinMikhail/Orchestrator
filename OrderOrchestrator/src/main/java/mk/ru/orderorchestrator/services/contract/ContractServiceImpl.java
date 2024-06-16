package mk.ru.orderorchestrator.services.contract;

import lombok.*;
import mk.ru.orderorchestrator.configurations.rest.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.util.*;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final RestConfiguration restConfiguration;
    private final WebClient webClientContract;

    @Override
    public UUID createContract(Long inn, Long accountNumber) {
        return webClientContract
                .get()
                .uri(restConfiguration.getContractService().getMethods().getCreateContract()
                        + "?inn=" + inn + "&accountNumber=" + accountNumber)
                .retrieve()
                .bodyToMono(UUID.class)
                .block();
    }

    @Override
    public void deleteContract(UUID contractId) {
        webClientContract
                .delete()
                .uri(restConfiguration.getContractService().getMethods().getDeleteContract() + "/" + contractId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
