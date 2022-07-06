package com.wei.spring.app.testweb.service;

import com.wei.spring.autoconfig.bean.AutoBeanA;
import com.wei.spring.autoconfig.bean.AutoBeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/7/3
 */
@Service
public class TestService {
    @Autowired
    private AutoBeanA autoBeanA;
    @Autowired
    private AutoBeanB autoBeanB;

    public void testA() {

    }
}
