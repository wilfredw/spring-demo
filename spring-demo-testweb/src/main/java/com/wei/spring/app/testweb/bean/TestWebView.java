package com.wei.spring.app.testweb.bean;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/7/3
 */
public class TestWebView {
    public TestWebView() {
        System.out.println("=== create TestWebView");
        this.name = "hello";
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
