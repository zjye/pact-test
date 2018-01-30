package com.example.payment;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.*;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "invoices.ribbon.listOfServers: localhost:8888"
        }
)
public class InvoiceClientTest {
    @Rule
    public PactProviderRuleMk2 stubProvider =
            new PactProviderRuleMk2("billingServiceProvider", "localhost", 8888, this);

    @Autowired
    private InvoiceClient invoiceClient;

    @Pact(state = "list of invoice",
    provider = "billingServiceProvider",
    consumer = "invoiceClient")
    public RequestResponsePact createInvoiceResourcePact(PactDslWithProvider builder) {

        DslPart body = PactDslJsonArray
                        .arrayEachLike()
                        .uuid("id")
                        .timestamp("createdOn");

        PactDslJsonArray body2 = new PactDslJsonArray().decimalType(100.10).stringType("Should be in an array");
        return builder
                .given("list of invoice")
                .uponReceiving("a request to the invoice resource")
                .path("/invoices")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createInvoiceResourcePact")
    public void verifyInvoiceListPact() {
        // arrange
        // act
        Object invoices = invoiceClient.get();

//        List<Invoice> something = asList(new Gson().fromJson(new Gson().toJson(invoices), Invoice[].class));
        // assert
//        assertThat(invoices).hasSize(1);
    }
}