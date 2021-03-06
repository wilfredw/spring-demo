package com.wei.spring.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by viruser on 2019/6/21.
 */
@RestController
public class TestEndPointController {


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id : " + id;
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        System.out.println(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        System.out.println(oAuth2Authentication.toString());
        System.out.println("principal.toString() " + principal.toString());
        System.out.println("principal.getName() " + principal.getName());
        System.out.println("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }
}