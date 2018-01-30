package com.example.payment;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.MessagePactProviderRule;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import com.google.gson.Gson;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class InvoiceCreatedMessageConsumerTest {

    @Rule
    public MessagePactProviderRule messagePactProviderRule = new MessagePactProviderRule(this);

    @Pact(provider = "invoiceCreatedMessageProvider", consumer = "payment_invoiceCreatedMessageConsumer")
    public MessagePact createInvoiceCreatedMessagePact(MessagePactBuilder builder) {
        PactDslJsonBody body = new PactDslJsonBody();
        body.uuid("id");
        body.object("invoice")
                .uuid("id")
                .timestamp("createdOn")
                .closeObject();

        return builder
                .given("billingJobState")
                .expectsToReceive("a InvoiceCreatedMessage")
                .withContent(body)
                .toPact();
    }


    @Test
    @PactVerification({"invoiceCreatedMessageProvider", "billingJobState"})
    public void shouldReceiveInvoiceCreatedMessage() throws Exception {
        String json = new String(messagePactProviderRule.getMessage());

        InvoiceCreatedMessage message = new Gson().fromJson(json, InvoiceCreatedMessage.class);

        assertThat(message).isNotNull();
    }
}
