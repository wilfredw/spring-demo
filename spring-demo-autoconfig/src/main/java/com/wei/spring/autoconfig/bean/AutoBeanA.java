package com.wei.spring.autoconfig.bean;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/7/3
 */
public class AutoBeanA {
    private String id;
    private String type;

    public AutoBeanA() {
        System.out.println("=== create AutoBeanA");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
