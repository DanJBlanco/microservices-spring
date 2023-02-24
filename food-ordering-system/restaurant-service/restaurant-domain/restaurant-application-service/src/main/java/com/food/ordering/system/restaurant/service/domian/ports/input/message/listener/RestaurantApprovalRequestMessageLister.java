package com.food.ordering.system.restaurant.service.domian.ports.input.message.listener;

import com.food.ordering.system.restaurant.service.domian.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageLister {

    void approvedOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
