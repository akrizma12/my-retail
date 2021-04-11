//package com.myproject.myretail.repository
//
//import com.myproject.myretail.domain.entity.Price
//import com.myproject.myretail.service.PriceService
//import spock.lang.Specification
//
//class PriceRespositorySpec extends Specification {
//
//  PriceService priceService = new PriceService()
//
//  def 'PriceRepository works' () {
//    setup:
//
//    when:
//    priceService.insertPrice()
//    List<Price> priceList = priceService.findAll()
//
//    then:
//    priceList.size() == 1;
//    priceList[0].price == 12.90
//
//  }
//}
