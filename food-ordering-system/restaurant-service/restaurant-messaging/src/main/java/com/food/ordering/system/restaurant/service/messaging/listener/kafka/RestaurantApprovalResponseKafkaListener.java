package com.food.ordering.system.restaurant.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.restaurant.service.domian.ports.input.message.listener.RestaurantApprovalRequestMessageListener;
import com.food.ordering.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalRequestAvroModel> {

    private final RestaurantApprovalRequestMessageListener restaurantApprovalRequestMessageListener;
    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;

    public RestaurantApprovalResponseKafkaListener(RestaurantApprovalRequestMessageListener restaurantApprovalResponseMessageListener,
                                                   RestaurantMessagingDataMapper restaurantMessagingDataMapper) {
        this.restaurantApprovalRequestMessageListener = restaurantApprovalResponseMessageListener;
        this.restaurantMessagingDataMapper = restaurantMessagingDataMapper;
    }

    @Override
    @KafkaListener(id ="${kafka-consumer-config.restaurant-approval-consumer-group-id}",
            topics = "${restaurant-service.restaurant-approval-request-topic-name}")
    public void receive(
            @Payload List<RestaurantApprovalRequestAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of orders approval requests received with keys:{}, partitions:{} and offsets:{} " +
                ", sending for restaurant approval",
                messages.size(), keys.toString(), partitions.toString(), offsets.toString());

        messages.forEach(restaurantApprovalRequestAvroModel -> {
              log.info("Processing order approval for order id: {}",
                        restaurantApprovalRequestAvroModel.getOrderId());
            restaurantApprovalRequestMessageListener.approvedOrder(
                    restaurantMessagingDataMapper
                            .restaurantApprovalRequestAvroModelToRestaurantApprovalRequest(
                                    restaurantApprovalRequestAvroModel));
        });
    }
}
