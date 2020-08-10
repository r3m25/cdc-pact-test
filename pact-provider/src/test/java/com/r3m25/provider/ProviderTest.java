package com.r3m25.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.r3m25.provider.service.ProviderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(
        value = ProviderService.class
)
@Provider("CustomerProvider")
@PactBroker(host = "localhost", port = "80")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProviderTest {

    @Autowired
    private MockMvc mockMvc;

    //@LocalServerPort
    //private int port;

    @BeforeAll
    public void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    /*@BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }*/

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new MockMvcTestTarget(mockMvc));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("customer consumer")
    public void someProviderState() {
        // Do what you need to set the correct state
    }

}
