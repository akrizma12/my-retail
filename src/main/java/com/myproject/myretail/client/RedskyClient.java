package com.myproject.myretail.client;

import com.myproject.myretail.domain.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class RedskyClient {

    @Value("${redskyClient.baseUrl}")
    String baseUrl;

    private final RestTemplate restTemplate;

    public Product getProductName(Integer productId) {
        String url = baseUrl+ productId+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";
        return restTemplate.getForObject(url, Product.class);
    }
}
