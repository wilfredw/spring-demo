package com.wei.spring.aop.config;

import com.wei.spring.aop.aspect.TestAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {
    @Bean
    public TestAspect testAspect() {
        return new TestAspect();
    }
}
