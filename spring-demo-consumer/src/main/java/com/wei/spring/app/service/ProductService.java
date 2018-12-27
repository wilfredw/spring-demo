package com.wei.spring.app.service;

import com.wei.spring.app.api.ProductApi;
import com.wei.spring.app.model.dto.ResultDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SERVICE-PROVIDER")
public interface ProductService extends ProductApi {

//    @RequestMapping(value = "/products/getProduct", method = RequestMethod.GET)
//    ResultDTO getProduct(@RequestParam("productId") String productId);

}
