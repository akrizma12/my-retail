package com.myproject.myretail.unitTest

import com.myproject.myretail.client.RedskyClient
import com.myproject.myretail.domain.entity.PriceEntity
import com.myproject.myretail.domain.product.ProductResponse
import com.myproject.myretail.repository.PriceRepository
import com.myproject.myretail.service.ProductService
import spock.lang.Specification

class ProductServiceSpec extends Specification{

  RedskyClient redskyClient = Mock();
  PriceRepository priceRepository = Mock();
  ProductService productService = new ProductService (redskyClient, priceRepository)

  def 'productService.getProductData calls redskyClient and priceRepository to return ProductResponse' () {
    setup:
    Integer productId = 12345

    when:
    ProductResponse productResponse = productService.getProductData(productId)

    then:
    1 * redskyClient.getProductName(productId) >> "product description"
    1 * priceRepository.findByProductId(productId) >> new PriceEntity(productId, 12.45, "USD")

    productResponse.id == productId
    productResponse.name == "product description"
    productResponse.currentPrice.value == 12.45
    productResponse.currentPrice.currencyCode == "USD"
  }

  def 'productService.getProductData when redskyClient and priceRepository returns null' () {
    setup:
    Integer productId = 12345

    when:
    ProductResponse productResponse = productService.getProductData(productId)

    then:
    1 * redskyClient.getProductName(productId) >> null
    1 * priceRepository.findByProductId(productId) >> null

    productResponse.id == productId
    productResponse.name == null
    productResponse.currentPrice == null
  }
}
