package com.example.billing;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.loader.PactUrl;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@Provider("billingServiceProvider")
@PactBroker(host="localhost", port = "80")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8080"
})
public class InvoiceControllerProviderTest {

    @TestTarget
    public final Target target = new HttpTarget(8080);


    @State({"list of invoice"})
    public void toCreatePersonState() {}
}
