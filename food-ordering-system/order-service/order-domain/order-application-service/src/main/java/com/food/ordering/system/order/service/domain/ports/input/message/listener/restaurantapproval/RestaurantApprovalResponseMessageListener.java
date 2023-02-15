package com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderApproval(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
