package mk.ru.orderorchestrator;

import com.github.tomakehurst.wiremock.*;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

import java.time.*;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.patchRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.lang.Thread.sleep;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ACCOUNT_NUMBER_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.CUSTOMER_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.DELIVERY_ADDRESS_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.INN_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.ORDER_ID_FIELD;
import static mk.ru.orderorchestrator.utils.CamundaUtils.TOTAL_PRICE_FIELD;

@ActiveProfiles("local")
@SpringBootTest
class OrderOrchestratorApplicationTests {
    @Autowired
    private RuntimeService runtimeService;

    WireMockServer wireMockServer;

    WireMockServer wireMockServerOrder;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().port(8083));
        wireMockServer.start();

        wireMockServerOrder = new WireMockServer(options().port(8081));
        wireMockServerOrder.start();
    }

    @Test
    void contextLoads() throws InterruptedException {
        UUID orderId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        wireMockServer.stubFor(get(urlEqualTo("/api/contract/create?inn=1234567890&accountNumber=1234"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("\"a5908cc6-386b-4d00-b780-6fb3f5cab48b\"")));

        wireMockServer.stubFor(post(urlEqualTo("/api/delivery/create"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("\"" + LocalDate.now().plusDays(7) + "\"")));

        wireMockServer.stubFor(post(urlEqualTo("/api/payment"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("SUCCESS")));

        wireMockServerOrder.stubFor(patch("/api/order/delivery").willReturn(aResponse().withStatus(200)));

        wireMockServerOrder.stubFor(patch("/api/order/" + orderId + "/status")
                .withRequestBody(matchingJsonPath("$.status", equalTo("CONFIRMED")))
                .willReturn(aResponse().withStatus(200)));

        ProcessInstance instance = runtimeService.createProcessInstanceByKey("OrchestratorProcessKey")
                .businessKey(UUID.randomUUID().toString())
                .setVariable("error", false)
                .setVariable(ORDER_ID_FIELD, orderId)
                .setVariable(INN_FIELD, 1234567890L)
                .setVariable(ACCOUNT_NUMBER_FIELD, 1234L)
                .setVariable(CUSTOMER_ID_FIELD, customerId)
                .setVariable(DELIVERY_ADDRESS_FIELD, "address")
                .setVariable(TOTAL_PRICE_FIELD, 100.0)
                .setVariable("СompilanceResult", "SUCCESS")
                .execute();

        String businessKey = instance.getBusinessKey();

        sleep(3000);

        runtimeService.createMessageCorrelation("СompilanceResult")
                .processInstanceBusinessKey(businessKey)
                .setVariable("СompilanceResult", "SUCCESS")
                .processInstanceBusinessKey(businessKey)
                .correlate();

        sleep(5000);

        wireMockServerOrder.verify(patchRequestedFor(urlEqualTo("/api/order/" + orderId + "/status"))
                .withRequestBody(matchingJsonPath("$.status", equalTo("CONFIRMED"))));
    }
}
