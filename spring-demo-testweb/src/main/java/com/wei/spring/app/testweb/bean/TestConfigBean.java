package com.wei.spring.app.testweb.bean;

import javax.annotation.PostConstruct;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/7/3
 */
public class TestConfigBean {
    public TestConfigBean() {
        System.out.println("=== create TestConfig");
        this.name = "config";
    }

    @PostConstruct
    public void init() {
        System.out.println("====== PostConstruct TestConfig ");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
