package com.wei.spring.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2019/10/29.
 */
@RestController
@RequestMapping("/hi")
public class HiController {
    @RequestMapping("/message")
    @ResponseBody
    public String hi() {
        return "hi";
    }
}
