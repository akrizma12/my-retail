package com.myproject.myretail.unitTest

import com.myproject.myretail.client.RedskyClient
import com.myproject.myretail.domain.product.ProductDetails
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RedSkyClientSpec extends Specification {

  RestTemplate restTemplate = Mock()
  String url = "testBaseUrl"
  RedskyClient redSkyClient = new RedskyClient(restTemplate)

  def 'redSkyClient.getProductName returns correct product name'() {
    setup:
    redSkyClient.setBaseUrl("testBaseUrl/")
    String inputUrl = "testBaseUrl/12345?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate"
    when:
    String productName = redSkyClient.getProductName(12345)

    then:
    1 * restTemplate.getForObject(inputUrl, ProductDetails.class) >> new ProductDetails(
      product: new ProductDetails.Product(
        item: new ProductDetails.Product.Item(
          productDescription: new ProductDetails.Product.Item.ProductDescription(
            title: "product title"
          )
        )
      )
    )
    noExceptionThrown()
    productName == "product title"
  }

//   TODO: this class doesnot pass
//  def 'redSkyClient.getProductName throws RestClientException'() {
//    setup:
//    redSkyClient.setBaseUrl("testBaseUrl/")
//    String inputUrl = "testBaseUrl/12345?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate"
//
//    when:
//    redSkyClient.getProductName(12345)
//
//    then:
//    1 * restTemplate.getForObject(inputUrl, ProductDetails.class, []) >> {throw new RestClientException()}
//    thrown(RestClientException)
//  }
}
