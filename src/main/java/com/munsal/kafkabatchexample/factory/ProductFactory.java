package com.munsal.kafkabatchexample.factory;

import com.munsal.kafkabatchexample.domain.entity.ProductEntity;
import com.munsal.kafkabatchexample.domain.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFactory {
    public ProductEntity toProductEntity(ProductEvent productEvent) {
        return ProductEntity.builder()
                .name(productEvent.getName())
                .price(productEvent.getPrice())
                .quantity(productEvent.getQuantity())
                .build();
    }

    public ProductEvent toProductEvent(ProductEntity productEntity) {
        return ProductEvent.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .build();
    }

}
