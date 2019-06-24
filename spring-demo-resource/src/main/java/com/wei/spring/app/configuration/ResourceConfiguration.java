package com.wei.spring.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by viruser on 2019/6/21.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 配置资源访问规则
        httpSecurity.authorizeRequests()
                .antMatchers("/order/**").authenticated();
    }
}
