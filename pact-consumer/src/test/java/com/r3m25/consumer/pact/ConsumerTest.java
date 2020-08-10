package com.r3m25.consumer.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "CustomerProvider")
@PactFolder("target/pacts/customer")
class ConsumerTest {

    @Pact(consumer="CustomerConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("customer consumer")
                .uponReceiving("Pact Test")
                .path("/customer")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonBody((o) -> {
                    o.stringValue("name", "ruben");
                    o.stringValue("lastName", "morales");
                    o.numberType("age", 31);
                    o.stringValue("address", "las violetas");
                    o.stringValue("phone", "965827070");
                }).build())
                .toPact();
    }

    @Test
    void test(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/customer").execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
    }

}
