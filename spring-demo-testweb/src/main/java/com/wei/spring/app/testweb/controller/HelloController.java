package com.wei.spring.app.testweb.controller;

import com.wei.spring.app.testweb.bean.TestConfigBean;
import com.wei.spring.app.testweb.bean.TestWebView;
import com.wei.spring.app.testweb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private TestWebView testWebView;
    @Autowired
    private TestConfigBean testConfigBean;

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String hello(String message) {
        return "hello " + message;
    }
}
