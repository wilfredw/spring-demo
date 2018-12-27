package com.wei.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@RestController
public class HelloController {
    @Resource(name="restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "lbcRestTemplate")
    private RestTemplate lbcRestTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return restTemplate.getForEntity("http://SERVICE-PROVIDER/hello", String.class).getBody();
    }

    @RequestMapping(value = "/helloEx", method = RequestMethod.GET)
    public String helloEx() {
        ServiceInstance instance = this.loadBalancerClient.choose("SERVICE-PROVIDER");
        URI helloUri = URI.create(String.format("http://%s:%s/hello", instance.getHost(), instance.getPort()));
        System.out.println("Target service uri = " + helloUri.toString());
        return this.lbcRestTemplate.getForEntity(helloUri, String.class).getBody();
    }




}
