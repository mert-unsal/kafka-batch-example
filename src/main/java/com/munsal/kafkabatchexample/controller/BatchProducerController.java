package com.munsal.kafkabatchexample.controller;

import com.munsal.kafkabatchexample.kafka.producer.BatchProducer;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BatchProducerController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class BatchProducerController {
    public static final String BASE_PATH = "batchProducer";
    private static final String TAG = "Courier Controllers";
    private final BatchProducer batchProducer;

    @PostMapping("produceRandomValues")
    @Operation(tags = TAG, summary = "Produce random events for simulation")
    public void getTotalTravelDistance(@RequestParam Integer numberOfMessage) {
        batchProducer.produceRandomData(numberOfMessage);
    }


}