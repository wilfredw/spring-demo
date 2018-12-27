package com.wei.spring.app.api;


import com.wei.spring.app.model.dto.ResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductApi {

    @RequestMapping(value = "/products/getProduct", method = RequestMethod.GET)
    ResultDTO getProduct(@RequestParam("productId") String productId);

}
