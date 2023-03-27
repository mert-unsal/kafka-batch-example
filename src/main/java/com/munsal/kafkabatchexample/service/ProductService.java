package com.munsal.kafkabatchexample.service;

import com.munsal.kafkabatchexample.domain.entity.ProductEntity;
import com.munsal.kafkabatchexample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void persistBatch(List<ProductEntity> productEventList) {
        List<ProductEntity> saveAll = (List<ProductEntity>) productRepository.saveAll(productEventList);
        log.info(saveAll.toString());
    }
}
