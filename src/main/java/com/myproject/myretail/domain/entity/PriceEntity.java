package com.myproject.myretail.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document
@AllArgsConstructor
public class PriceEntity {
    @Field("product_id")
    private Integer productId;
    private Double price;
    @Field("currency_code")
    private String currencyCode;
}
