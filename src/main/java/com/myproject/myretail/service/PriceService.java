package com.myproject.myretail.service;

import com.myproject.myretail.domain.entity.Price;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    MongoTemplate mongoTemplate;

    public PriceService( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public void insert(Price price) {
        mongoTemplate.insert(price);
    }

    public List<Price> findAll() {
        List<Price> prices = mongoTemplate.findAll(Price.class);
        return prices;
    }


}
