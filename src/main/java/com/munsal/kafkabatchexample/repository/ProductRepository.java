package com.munsal.kafkabatchexample.repository;

import com.munsal.kafkabatchexample.domain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository  extends CrudRepository<ProductEntity, Long> {
}
