package com.food.ordering.system.order.service.messaging.publisher.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderKafkaMessageHelper {


    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(
            String responseTopicName,
            T requestAvroModel,
            String orderId,
            String requestAvroModelName) {
        return (result, err) -> {
            if (err != null) {
                log.error("Error while sending {} message {} to topic {}",
                        requestAvroModelName, requestAvroModel.toString(), responseTopicName, err);
            }
            else  {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} offset: {} Timestamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }
}
