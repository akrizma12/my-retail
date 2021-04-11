package com.myproject.myretail.service;

import com.myproject.myretail.client.RedskyClient;
import com.myproject.myretail.domain.entity.Price;
import com.myproject.myretail.domain.product.Product;
import com.myproject.myretail.domain.product.ProductResponse;
import com.myproject.myretail.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final RedskyClient redskyClient;

    private final PriceRepository priceRepository;

    public ProductResponse getProductData(Integer productId) {
        Product product = redskyClient.getProductName(productId);
        Price price = priceRepository.findByProductId(productId);
        return new ProductResponse(
                productId,
                product.getName(),
                new ProductResponse.CurrentPrice(price.getPrice(), price.getCurrencyCode())
        );
    }
}
