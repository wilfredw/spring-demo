package com.wei.spring.app.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by viruser on 2019/7/27.
 */
@RestController
@RequestMapping("/security/user")
public class HelloController {

    @RequestMapping("/hi")
    @ResponseBody
    public String hello() {
        return "Hi, nice to meet you!";
    }

    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("user_id", 123456);
        return json.toJSONString();
    }

    @RequestMapping("/name")
    @ResponseBody
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        } else {
            return "who are you!";
        }
    }

    @RequestMapping("/principal")
    @ResponseBody
    public String principal(Principal principal) {
        return principal.toString();
    }

    @RequestMapping("/authentication")
    @ResponseBody
    public String authentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        return authentication.toString();
    }

    @RequestMapping(value = "/requestinfo", method = RequestMethod.GET)
    @ResponseBody
    public String requestinfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
}
