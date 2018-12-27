package com.wei.spring.app.controller;

import com.wei.spring.app.model.dto.ResultDTO;
import com.wei.spring.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "getProduct", method = RequestMethod.GET)
    public ResultDTO getProduct(@RequestParam("productId") String productId) {
        return this.productService.getProduct(productId);
    }

}
