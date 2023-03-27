package com.munsal.kafkabatchexample.kafka.listener;

import com.munsal.kafkabatchexample.domain.event.EventValidationWrapper;
import com.munsal.kafkabatchexample.domain.event.ProductEvent;
import com.munsal.kafkabatchexample.factory.ProductFactory;
import com.munsal.kafkabatchexample.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductBatchEventListener implements BatchEventListener<ProductEvent> {
    private final ProductService productService;
    private final ProductFactory productFactory;

    @Override
    public void processBatchEvent(List<ProductEvent> productEventList) {
        productService.persistBatch(productEventList.stream().map(productFactory::toProductEntity).toList());
    }

    @Override
    public void handleInValidEvent(List<ProductEvent> productEventList) {
        productEventList.forEach(productEvent -> {
            log.error("ProductEvent : {} is not valid to be proceed", productEvent);
        });
    }

    @Override
    public EventValidationWrapper<ProductEvent> validateBatchEvent(List<ProductEvent> batchEvent) {
        List<ProductEvent> validEvents = new ArrayList<>();
        List<ProductEvent> invalidEvents = new ArrayList<>();
        batchEvent.forEach(productEvent ->
            Optional.ofNullable(productEvent)
                    .filter(event -> Objects.nonNull(event.getId()))
                    .filter(event -> ObjectUtils.isNotEmpty(event.getName()))
                    .ifPresentOrElse(productEvent_ -> validEvents.add(productEvent),()-> invalidEvents.add(productEvent)));
        return EventValidationWrapper.<ProductEvent>builder().validEventList(validEvents).invalidEventList(invalidEvents).build();
    }

}
