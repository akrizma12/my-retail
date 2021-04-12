package com.myproject.myretail.controller;

import com.myproject.myretail.domain.product.Price;
import com.myproject.myretail.domain.product.ProductResponse;
import com.myproject.myretail.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products/{productId}")
    ProductResponse product(@PathVariable Integer productId) {
        return productService.getProductData(productId);
    }

    @PutMapping(value = "/products/{productId}")
    ResponseEntity<String> product(@PathVariable Integer productId, @RequestBody Price price) {
        productService.updateProductPrice(productId, price);
        return new ResponseEntity("Updated", HttpStatus.OK);
    }
}
