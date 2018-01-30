package com.example.billing;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@RestController("/v2/")
public class InvoiceController {

    @RequestMapping(path = "/invoices",
            method = RequestMethod.GET)
    public List<Invoice> get() {

        return asList(new Invoice(),
                new Invoice());
    }

}
