package com.example.payment;

import java.util.UUID;

public class InvoiceCreatedMessage {
    private UUID id;
    private Invoice invoice;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
