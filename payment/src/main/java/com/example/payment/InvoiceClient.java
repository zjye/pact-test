package com.example.payment;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "invoices", path = "/invoices")
public interface InvoiceClient {
    @RequestMapping(method = RequestMethod.GET, params = "/")
    Object get();
}
