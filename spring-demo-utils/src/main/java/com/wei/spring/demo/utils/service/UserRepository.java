package com.wei.spring.demo.utils.service;

import org.springframework.stereotype.Repository;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/3/15
 */
@Repository
public class UserRepository {
    public String getName(String id) {
        return "jack" + id;
    }
}
