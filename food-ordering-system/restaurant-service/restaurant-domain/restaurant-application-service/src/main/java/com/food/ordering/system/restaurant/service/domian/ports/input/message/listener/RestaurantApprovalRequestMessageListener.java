package com.food.ordering.system.restaurant.service.domian.ports.input.message.listener;

import com.food.ordering.system.restaurant.service.domian.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {

    void approvedOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
