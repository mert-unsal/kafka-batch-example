package com.munsal.kafkabatchexample.kafka.producer;

import com.munsal.kafkabatchexample.domain.event.ProductEvent;
import com.munsal.kafkabatchexample.util.Util;
import com.munsal.kafkaconfiguration.kafka.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BatchProducer {

    private final static String PRODUCER_NAME = "default";
    @Value("${kafka-configuration.topics.case-study-consumer-topic}")
    private String topic;
    private final KafkaSender kafkaSender;

    public void produceRandomData(Integer numberOfMessage) {
        List<ProductEvent> productEvents = new ArrayList<>();
        IntStream.range(0, numberOfMessage)
                .forEach(index -> {
                    productEvents.add(ProductEvent.builder().id(numberOfMessage.longValue()).price((double) Util.getRandomNumberUsingNextInt(1, 100000)).name(Util.getGeneratedString()).quantity(Util.getRandomNumberUsingNextInt(1, 100)).build());
                });
        kafkaSender.sendBatch(PRODUCER_NAME,topic,productEvents);
    }

}
