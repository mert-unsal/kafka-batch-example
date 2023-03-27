package com.munsal.kafkabatchexample.kafka.listener;

import com.munsal.kafkabatchexample.domain.event.EventValidationWrapper;

import java.util.List;
import java.util.Optional;

public interface BatchEventListener<T> {
    default void handleBatchEvent(List<T> batchEvent) {
        EventValidationWrapper<T> eventValidationWrapper = validateBatchEvent(batchEvent);
        Optional.ofNullable(eventValidationWrapper).map(EventValidationWrapper::getValidEventList).ifPresent(this::processBatchEvent);
        Optional.ofNullable(eventValidationWrapper).map(EventValidationWrapper::getInvalidEventList).ifPresent(this::handleInValidEvent);
    }
    EventValidationWrapper<T> validateBatchEvent(List<T> event);
    void processBatchEvent(List<T> event);
    void handleInValidEvent(List<T> event);
}