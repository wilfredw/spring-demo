package com.wei.spring.autoconfig;

import com.wei.spring.autoconfig.bean.AutoBeanA;
import com.wei.spring.autoconfig.bean.AutoBeanB;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author buhuan.wang
 * @since 2022/7/3
 */
@Configuration
@AutoConfigureAfter(TestAutoConfigurationA.class)
public class TestAutoConfigurationB {

    @Bean
    @ConditionalOnBean(value = AutoBeanA.class)
    public AutoBeanB autoBeanB(AutoBeanA autoBeanA) {
        return new AutoBeanB(autoBeanA);
    }

}
