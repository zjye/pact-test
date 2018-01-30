package com.example.billing;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringMessagePactRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@RunWith(SpringMessagePactRunner.class)
@Provider("invoiceCreatedMessageProvider")
@PactBroker(host="localhost", port = "80")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8080"
})
public class InvoiceCreatedMessageProviderTest {

    @TestTarget
    public final Target target = new AmqpTarget();

    @State("InvoiceCreatedProviderState")
    public void invoiceCreatedProviderState() {}


    @Autowired
    private ObjectMapper mapper;

    @PactVerifyProvider("a InvoiceCreatedMessage")
    public String verifyMessageForInvoiceCreated() throws JsonProcessingException {
        InvoiceCreatedMessage message = new InvoiceCreatedMessage();
        message.setId(UUID.randomUUID());
        message.setInvoice(new Invoice());

        return mapper.writeValueAsString(message);
    }
}
