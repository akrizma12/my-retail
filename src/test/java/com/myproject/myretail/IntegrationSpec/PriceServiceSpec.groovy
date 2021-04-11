package com.myproject.myretail.IntegrationSpec

import com.myproject.myretail.domain.entity.Price
import com.myproject.myretail.service.PriceService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import spock.lang.Specification

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class PriceServiceSpec extends Specification{

  MongoTemplate mongoTemplate
  PriceService priceService = new PriceService(mongoTemplate);

//  @Autowired
//  PriceRepository priceRepository

  def 'PriceService works' () {
    setup:
    Price price = new Price()
    price.setPrice(12.90);
    price.setProductId(123);
    price.setCurrencyCode("USD");

    when:
    priceService.insert(price)
    List<Price> priceList = priceService.findAll()

    then:
    priceList.size() == 1;
    priceList[0].price == 12.90
  }
}
