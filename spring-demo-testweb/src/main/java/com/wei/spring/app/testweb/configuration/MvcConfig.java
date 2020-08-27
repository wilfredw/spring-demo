package com.wei.spring.app.testweb.configuration;

import com.wei.spring.app.testweb.interceptor.HiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //  注入拦截器
    @Bean
    public HiInterceptor getHiInterceptor(){
        return  new HiInterceptor();
    }
    //   把自定义的拦截器添加到mvc 配置中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getHiInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login","/error"
                ,"/**/*.jsp"
                ,"/**/*.js","/**/*.css","/**/font/*");
    }
}