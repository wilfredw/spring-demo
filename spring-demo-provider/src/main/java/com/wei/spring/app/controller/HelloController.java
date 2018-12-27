package com.wei.spring.app.controller;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.wei.spring.app.model.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
public class HelloController {


    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    @Value("${server.port}")
    private int serverPort = 0;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResultDTO hello() {
        ResultDTO resultDTO = new ResultDTO();
        System.out.println("/hello, instanceId and host is " + eurekaInstanceConfig.getInstanceId() + eurekaInstanceConfig.getHostName(false));
        resultDTO.setSuccess("Hello, Spring Cloud! My port is " + String.valueOf(serverPort));
        return resultDTO;
    }


}
