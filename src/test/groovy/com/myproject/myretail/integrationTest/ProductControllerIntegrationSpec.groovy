package com.myproject.myretail.integrationTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.myproject.myretail.domain.entity.PriceEntity
import com.myproject.myretail.repository.PriceRepository
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class ProductControllerIntegrationSpec extends Specification {

  @Autowired
  MockMvc mockMvc

  @Autowired
  PriceRepository priceRepository

  @Autowired
  ObjectMapper objectMapper

  void setup() {
    priceRepository.deleteAll()
  }

  def 'get request to /products/{productId} endpoint returns Product response' () {
    setup:
    PriceEntity originalPriceEntity = new PriceEntity(13860428, 32.00, "USD" )
    priceRepository.save(originalPriceEntity)

    String expectedResponse = '''
    {
      "id": 13860428,
      "name": "The Big Lebowski (Blu-ray)",
      "currentPrice": 
      {
        "value": 32.00,
        "currencyCode": "USD"
      }
    }
    '''

    String url = "/products/13860428"
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)

    when:
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn()

    then:
    mvcResult.response.status == 200;
    String actualResponse = mvcResult.response.contentAsString;
    JSONAssert.assertEquals(actualResponse, expectedResponse, JSONCompareMode.STRICT)
  }

  def 'put request to /products/{productId} updates the price of product with productId={productId}'() {
    setup:
    Integer productId = 12345678
    PriceEntity originalPriceEntity = new PriceEntity(productId, 32.00, "EURO" )
    String updatedPriceRequest = '''
      {
        "value": 23.90,
        "currencyCode": "USD"
      }
      '''
    String url = "/products/12345678"

    when: 'we first save priceEntity with productId = 12345678'
    priceRepository.save(originalPriceEntity)
    
    then: 'we get priceEntity of size 1 which we had just saved'
    List<PriceEntity> priceEntities = priceRepository.findAll()
    
    priceEntities.size() == 1
    priceEntities[0].productId == originalPriceEntity.productId
    priceEntities[0].price == originalPriceEntity.price
    priceEntities[0].currencyCode == originalPriceEntity.currencyCode
    
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON).content(updatedPriceRequest)
      
    when: 'we update the price of the same product with updatedPriceRequest using /products/${productId endpoint'
    mockMvc.perform(requestBuilder).andReturn()
    List<PriceEntity> updatedPriceEntities = priceRepository.findAll()

    then: 'we get updated price of the product'
    updatedPriceEntities.size() == 1;
    updatedPriceEntities[0].productId == originalPriceEntity.productId
    updatedPriceEntities[0].price == 23.90
    updatedPriceEntities[0].currencyCode == "USD"
  }
}
