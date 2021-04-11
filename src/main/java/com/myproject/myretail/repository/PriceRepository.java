package com.myproject.myretail.repository;

import com.myproject.myretail.domain.entity.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PriceRepository extends MongoRepository<Price, Integer> {

    Price findByProductId(Integer productId);
}
