package com.wei.spring.app.controller;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.wei.spring.app.api.ProductApi;
import com.wei.spring.app.model.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/products")
public class ProductController implements ProductApi {

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    @Value("${server.port}")
    private int serverPort = 0;

//    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    @Override
    public ResultDTO getProduct(@RequestParam String productId) {
        ResultDTO resultDTO = new ResultDTO();
        System.out.println("/hello, instanceId and host is " + eurekaInstanceConfig.getInstanceId() + eurekaInstanceConfig.getHostName(false));
        resultDTO.setSuccess("Product Id is " + productId);
        return resultDTO;
    }

}

