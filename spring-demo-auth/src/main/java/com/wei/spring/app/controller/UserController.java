package com.wei.spring.app.controller;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by viruser on 2019/6/21.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @RequestMapping(value="/current", method = {RequestMethod.GET, RequestMethod.POST})
    public Principal getUser(Principal principal) {
        System.out.println("request principal: " + principal.toString());
        return principal;
    }
}
