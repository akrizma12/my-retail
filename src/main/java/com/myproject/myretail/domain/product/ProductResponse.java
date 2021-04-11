package com.myproject.myretail.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {
    Integer id;
    String name;
    CurrentPrice current_price;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CurrentPrice {
        Double value;
        String currencyCode;
    }
}
