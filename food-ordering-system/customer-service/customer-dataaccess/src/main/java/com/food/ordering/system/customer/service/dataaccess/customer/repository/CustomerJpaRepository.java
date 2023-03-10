package com.food.ordering.system.customer.service.dataaccess.customer.repository;

import com.food.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
