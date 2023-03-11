package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;

import java.util.UUID;

public class Customer extends AggregateRoot<CustomerId> {

    private String username;
    private String lastName;
    private String firstName;

    public Customer(CustomerId customerId, String username,  String firstName, String lastName) {
        super.setId(customerId);
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
