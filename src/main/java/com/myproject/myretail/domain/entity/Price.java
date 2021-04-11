package com.myproject.myretail.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Price {

    Integer productId;
    Double price;
    String currencyCode;
}
