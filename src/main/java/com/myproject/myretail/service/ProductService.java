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
        PriceEntity priceEntity = priceRepository.findPriceEntityByProductId(productId);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productId);
        productResponse.setName(productName);

        if (priceEntity != null) {
            Price price = new Price(priceEntity.getPrice(), priceEntity.getCurrencyCode());
            productResponse.setCurrentPrice(price);
        }
        return productResponse;
    }

    public void updateProductPrice(Integer productId, Price price) {
        PriceEntity priceEntity = new PriceEntity(productId, price.getValue(), price.getCurrencyCode());
        if (priceRepository.findPriceEntityByProductId(productId) == null) {
            priceRepository.save(priceEntity);
        } else {
            priceRepository.deleteByProductId(productId);
            priceRepository.save(priceEntity);
        }
    }
}
