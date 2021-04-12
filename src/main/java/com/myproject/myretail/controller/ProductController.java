package com.myproject.myretail.controller;

import com.myproject.myretail.domain.product.Price;
import com.myproject.myretail.domain.product.ProductResponse;
import com.myproject.myretail.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProductResponse product(@PathVariable Integer productId) {
        return productService.getProductData(productId);
    }

    @PutMapping(value = "/products/{productId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Price product(@PathVariable Integer productId, @RequestBody Price price) {
        return productService.updateProductPriceAndReturn(productId, price);
    }
}
