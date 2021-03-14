package com.wei.spring.demo.utils.controller;

import com.wei.spring.demo.utils.model.dto.LoginCmd;
import com.wei.spring.demo.utils.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/14
 */
@RestController
@ResponseBody
@RequestMapping("/uesr")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(String id) {
        return userService.login(LoginCmd.builder().id(id).build()).toString();
    }
}
