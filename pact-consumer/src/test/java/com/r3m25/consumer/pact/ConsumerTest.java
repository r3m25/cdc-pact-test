package com.r3m25.consumer.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import org.apache.commons.io.IOUtils;
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
    public RequestResponsePact createPactForCustomer(PactDslWithProvider builder) {
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
                    o.numberType("address", 234);
                    o.numberType("phone", 965827070);
                }).build())
                .toPact();
    }

    @Pact(consumer="ComputerConsumer")
    public RequestResponsePact createPactForComputer(PactDslWithProvider builder) {
        return builder
                .given("computer consumer")
                .uponReceiving("Test interaction with provider computer")
                .path("/computer")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonBody((dataObject) -> {
                    dataObject.numberType("id", 98746);
                    dataObject.stringValue("type", "pc");
                    dataObject.stringValue("brand", "asus");
                    dataObject.stringValue("model", "rog");
                    dataObject.object("card", (card) -> {
                        card.numberType("giga", 16);
                        card.numberType("cycle", 3600);
                    });
                    dataObject.object("processor", (processor) -> {
                        processor.numberType("cores", 9);
                        processor.numberType("cache", 12);
                    });
                    dataObject.object("ram", (processor) -> {
                        processor.numberType("memory", 2);
                        processor.numberType("capacity", 12);
                    });
                    dataObject.array("decorators", array -> {
                        array.stringValue("tower");
                        array.stringValue("lights");
                    });
                    dataObject.array("component", array -> {
                        array.object(dataObjectArray -> {
                            dataObjectArray.stringValue("brand", "Logitech");
                            dataObjectArray.stringValue("model", "z5500");
                        });
                        array.object(dataObjectArray -> {
                            dataObjectArray.stringValue("brand", "Logitech");
                            dataObjectArray.stringValue("model", "z5500");
                        });
                    });
                }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForCustomer")
    void testCustomer(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/customer").execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
        assertThat(IOUtils.toString(httpResponse.getEntity().getContent()),
                is(equalTo("{\"lastName\":\"morales\",\"address\":234,\"phone\":965827070,\"name\":\"ruben\",\"age\":31}")));
    }

    @Test
    @PactTestFor(pactMethod = "createPactForComputer")
    void testComputer(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/computer").execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));

    }

}
