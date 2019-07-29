package com.wei.spring.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2019/7/27.
 */
@RestController
@RequestMapping("/security")
public class HelloController {

    @RequestMapping("/hi")
    @ResponseBody
    public String hello() {
        return "Hi, nice to meet you!";
    }
}
