package com.wei.spring.autoconfig;

import com.wei.spring.autoconfig.bean.AutoBeanA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author buhuan.wang
 * @since 2022/7/3
 */
@Configuration
public class TestAutoConfigurationA {
    @Bean
    public AutoBeanA autoBeanA() {
        return new AutoBeanA();
    }
}
