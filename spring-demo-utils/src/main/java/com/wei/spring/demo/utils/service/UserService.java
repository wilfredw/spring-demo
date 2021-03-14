package com.wei.spring.demo.utils.service;

import com.wei.spring.demo.utils.converter.UserConverter;
import com.wei.spring.demo.utils.model.dto.LoginCmd;
import com.wei.spring.demo.utils.model.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/14
 */
@Service
public class UserService {
    @Autowired
    private UserConverter userConverter;

    public UserVO login(LoginCmd loginCmd) {
        return userConverter.toVO(loginCmd);
    }

    public String getType(String id) {
        return "type" + id;
    }
}
