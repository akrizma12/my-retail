package com.myproject.myretail.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private Price currentPrice;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CurrentPrice {
        private Double value;
        private String currencyCode;
    }
}
