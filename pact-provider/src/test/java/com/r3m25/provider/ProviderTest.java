package com.r3m25.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.r3m25.provider.domain.*;
import com.r3m25.provider.service.ComputerService;
import com.r3m25.provider.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("CustomerProvider")
@PactBroker(host = "localhost", port = "80")
class ProviderTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ComputerService computerService;

    @LocalServerPort
    private Integer port;

    @BeforeAll
    void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("customer consumer")
    public void customerProvider() {
        reset(customerService);
        Customer buildCustomer = Customer.builder()
                .name("ruben")
                .lastName("morales")
                .age(31)
                .email("ruben.morales@globant.com")
                .address(234)
                .phone(965827070)
                .build();

        when(customerService.getCustomer()).thenReturn(buildCustomer);

    }

    @State("computer consumer")
    public void computerProvider() {
        reset(computerService);
        String[] decorators = new String[2];
        decorators[0] = "tower";
        decorators[1] = "lights";

        Computer buildComputer = Computer.builder()
                .id(0)
                .type("pc")
                .brand("asus")
                .model("rog")
                .card(Card.builder().giga(16).cycle(3600).build())
                .processor(Processor.builder().cache(12).cores(8).build())
                .ram(Ram.builder().memory(2).capacity(12).build())
                .decorators(decorators)
                .component(Arrays.asList(
                        Component.builder().brand("Logitech").model("z5500").build(),
                        Component.builder().brand("Logitech").model("z5500").build()))
                .build();

        when(computerService.getComputer()).thenReturn(buildComputer);

    }

}
