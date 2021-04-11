package com.myproject.myretail.client;

import com.myproject.myretail.domain.product.ProductDetails;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@Setter
public class RedskyClient {

    @Value("${redskyClient.baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public RedskyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductName(Integer productId) {
        String url = baseUrl+ productId+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate";
        try {
            ProductDetails productDetails = restTemplate.getForObject(url, ProductDetails.class);
            if (productDetails != null) {
                return productDetails.getProduct().getItem().getProductDescription().getTitle();
            }
            return null;
        } catch (RestClientException ex) {
            log.error("Error on RedskyClient.RedskyClient, " + ex.getMessage());
            throw ex;
        }
    }
}
