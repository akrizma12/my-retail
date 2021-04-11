package com.myproject.myretail.service;

import com.myproject.myretail.client.RedskyClient;
import com.myproject.myretail.domain.entity.PriceEntity;
import com.myproject.myretail.domain.product.Price;
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
        String productName = redskyClient.getProductName(productId);
        PriceEntity priceEntity = priceRepository.findByProductId(productId);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productId);
        if (productName != null) {
            productResponse.setName(productName);
        }
        if (priceEntity != null) {
            Price currentPriceEntity = new Price(priceEntity.getPrice(), priceEntity.getCurrencyCode());
            productResponse.setCurrentPrice(currentPriceEntity);
        }
        return productResponse;
    }

    public void updateProductPrice(Integer productId, Price price) {
        PriceEntity priceEntity = new PriceEntity(productId, price.getValue(), price.getCurrencyCode());
        if (priceRepository.findByProductId(productId) == null) {
            priceRepository.save(priceEntity);
        } else {
            priceRepository.deleteByProductId(productId);
            priceRepository.save(priceEntity);
        }
    }
}
