package com.munsal.kafkabatchexample.kafka.consumer;

import com.munsal.kafkabatchexample.domain.event.ProductEvent;
import com.munsal.kafkabatchexample.kafka.listener.ProductBatchEventListener;
import com.munsal.kafkaconfiguration.annotation.DependsOnKafkaConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@DependsOnKafkaConfiguration
@ConditionalOnProperty(value = "kafka-configuration.consumers[product-case-study-batch-consumer].enabled", havingValue = "true")
public class ProductEventConsumer {
    private final ProductBatchEventListener productBatchEventListener;

    @KafkaListener(topics = "${kafka-configuration.consumers[product-case-study-batch-consumer].topic}",
            groupId = "${kafka-configuration.consumers[product-case-study-batch-consumer].props[group.id]}",
            containerFactory = "#{kafkaListenerContainerFactoryMap['product-case-study-batch-consumer']}")
    public void listen(@Payload List<ProductEvent> productEventList,
                       @Header(KafkaHeaders.RECEIVED_KEY) List<Integer> keys,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                       @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        try {
            productBatchEventListener.handleBatchEvent(productEventList);
        } catch (Exception exception) {
            throw exception;
        }
    }


}
