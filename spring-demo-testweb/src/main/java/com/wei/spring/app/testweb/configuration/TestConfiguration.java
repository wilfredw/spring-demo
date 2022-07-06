package com.wei.spring.app.testweb.configuration;

import com.wei.spring.app.testweb.bean.TestConfigBean;
import com.wei.spring.app.testweb.bean.TestWebView;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/7/3
 */
@Configuration
public class TestConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = TestWebView.class)
    public TestConfigBean testConfig() {
        return new TestConfigBean();
    }
}
