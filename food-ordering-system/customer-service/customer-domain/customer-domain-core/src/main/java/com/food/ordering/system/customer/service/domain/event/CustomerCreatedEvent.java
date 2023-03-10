package com.food.ordering.system.customer.service.domain.event;

import com.food.ordering.system.customer.service.domain.entity.Customer;
import com.food.ordering.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private final ZonedDateTime createAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createAt) {
        this.customer = customer;
        this.createAt = createAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
