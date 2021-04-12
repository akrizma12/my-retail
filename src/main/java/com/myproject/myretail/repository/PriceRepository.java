package com.myproject.myretail.repository;

import com.myproject.myretail.domain.entity.PriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PriceRepository extends MongoRepository<PriceEntity, Integer> {

    PriceEntity findPriceEntityByProductId(Integer productId);

    void deleteByProductId(Integer productId);
}
