package com.example.payment;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

public class Invoice {
    private UUID id;
    private String createdOn;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
