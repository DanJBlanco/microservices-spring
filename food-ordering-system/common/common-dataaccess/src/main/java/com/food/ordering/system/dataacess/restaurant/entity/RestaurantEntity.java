package com.food.ordering.system.dataacess.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class RestaurantEntity extends RestaurantEntityId {

    @Id
    private UUID restaurantId;

    @Id
    private UUID productId;

    private String restaurantName;
    private Boolean restaurantActive;
    private String productName;
    private BigDecimal productPrice;
    private Boolean productAvailable;

    @Builder(builderMethodName = "RestaurantEntityBuilder")
    public RestaurantEntity(UUID restaurantId, UUID productId, UUID restaurantId1, UUID productId1, String restaurantName, Boolean restaurantActive, String productName, BigDecimal productPrice) {
        super(restaurantId, productId);
        this.restaurantId = restaurantId1;
        this.productId = productId1;
        this.restaurantName = restaurantName;
        this.restaurantActive = restaurantActive;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public RestaurantEntity(UUID restaurantId, UUID productId, String restaurantName, Boolean restaurantActive, String productName, BigDecimal productPrice) {
        this.restaurantId = restaurantId;
        this.productId = productId;
        this.restaurantName = restaurantName;
        this.restaurantActive = restaurantActive;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restaurantId, productId);
    }
}
