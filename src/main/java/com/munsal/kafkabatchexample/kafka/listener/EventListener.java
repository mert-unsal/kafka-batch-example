package com.munsal.kafkabatchexample.kafka.listener;

public interface EventListener<T> {
    default void handleEvent(T event) {
        if(validate(event)) {
            processEvent(event);
        } else {
            handleInValidEvent(event);
        }
    }
    Boolean validate(T event);
    void processEvent(T event);
    void handleInValidEvent(T event);
}