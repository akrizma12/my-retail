package com.myproject.myretail.service;

import com.myproject.myretail.client.RedskyClient;
import com.myproject.myretail.domain.entity.PriceEntity;
import com.myproject.myretail.domain.product.Price;
import com.myproject.myretail.domain.product.ProductResponse;
import com.myproject.myretail.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Price updateProductPriceAndReturn(Integer productId, Price price) {
        PriceEntity priceEntity = new PriceEntity(productId, price.getValue(), price.getCurrencyCode());
        if (priceRepository.findPriceEntityByProductId(productId) == null) {
            priceRepository.save(priceEntity);
        } else {
            updateProductPriceAndReturn(priceEntity, productId);
        }
        PriceEntity updatedPriceEntity = priceRepository.findPriceEntityByProductId(productId);
        return new Price(updatedPriceEntity.getPrice(), updatedPriceEntity.getCurrencyCode());
    }

    @Transactional
    public void updateProductPriceAndReturn(PriceEntity priceEntity, Integer productId) {
        priceRepository.deleteByProductId(productId);
        priceRepository.save(priceEntity);
    }
}
