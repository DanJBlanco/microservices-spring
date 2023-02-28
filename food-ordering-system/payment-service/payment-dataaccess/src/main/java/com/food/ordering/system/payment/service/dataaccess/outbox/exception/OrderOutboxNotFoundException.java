package com.food.ordering.system.payment.service.dataaccess.outbox.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class OrderOutboxNotFoundException extends RuntimeException{

    public OrderOutboxNotFoundException(String message) {
        super(message);
    }
}
