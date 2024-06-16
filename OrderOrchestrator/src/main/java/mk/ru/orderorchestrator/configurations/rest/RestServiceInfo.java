package mk.ru.orderorchestrator.configurations.rest;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestServiceInfo {
    private String host;
    private Methods methods;
}
