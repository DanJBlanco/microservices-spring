package com.food.ordering.system.customer.service.messaging.publisher.kafka;

import com.food.ordering.system.customer.service.domain.config.CustomerServiceConfigData;
import com.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;
import com.food.ordering.system.customer.service.domain.ports.output.message.publisher.CustomerMessagePublisher;
import com.food.ordering.system.customer.service.messaging.mapper.CustomerMessagingDataMapper;
import com.food.ordering.system.kafka.order.avro.model.CustomerAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class CustomerCreatedEventKafkaPublisher implements CustomerMessagePublisher {

    private final CustomerMessagingDataMapper customerMessagingDataMapper;
    private final KafkaProducer<String, CustomerAvroModel> kafkaProducer;
    private final CustomerServiceConfigData customerServiceConfigData;

    public CustomerCreatedEventKafkaPublisher(CustomerMessagingDataMapper customerMessagingDataMapper,
                                              KafkaProducer<String, CustomerAvroModel> kafkaProducer,
                                              CustomerServiceConfigData customerServiceConfigData) {
        this.customerMessagingDataMapper = customerMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.customerServiceConfigData = customerServiceConfigData;
    }

    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Received CustomerCreatedEvent for customer id: {}", customerCreatedEvent.getCustomer().getId().getValue());

        try {
            CustomerAvroModel customerAvroModel =
                    customerMessagingDataMapper.paymentResponseAvroModelToCustomerAvroModel(customerCreatedEvent);

            kafkaProducer.send(customerServiceConfigData.getCustomerTopicName(),
                    customerAvroModel.getId(),
                    customerAvroModel,
                    getCallback(customerServiceConfigData.getCustomerTopicName(), customerAvroModel));

            log.info("CustomerCreatedEvent sent to kafka for customer id: {}", customerAvroModel.getId());
        } catch (Exception e) {
            log.error("Error while sending CustomerCreatedEvent to kafka for customer id: {}, error: {}",
                    customerCreatedEvent.getCustomer().getId().getValue(), e.getMessage());

        }
    }

    private BiConsumer<SendResult<String, CustomerAvroModel>, Throwable> getCallback(String customerTopicName,
                                                                                     CustomerAvroModel customerAvroModel) {
        return new BiConsumer<SendResult<String, CustomerAvroModel>, Throwable>() {
            @Override
            public void accept(SendResult<String, CustomerAvroModel> stringCustomerAvroModelSendResult, Throwable throwable) {

            }
        };
    }
}
