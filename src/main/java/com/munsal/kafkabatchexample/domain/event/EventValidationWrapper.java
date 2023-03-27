package com.munsal.kafkabatchexample.domain.event;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EventValidationWrapper<T> {
    private List<T> validEventList;
    private List<T> invalidEventList;
}
