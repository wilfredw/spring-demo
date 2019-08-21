package com.wei.spring.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import javax.servlet.ServletContext;

/**
 * Created by viruser on 2019/7/29.
 */
@Configuration
@EnableRedisHttpSession
public class SpringSessionConfiguration {
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public CookieSerializer cookieSerializer(ServletContext servletContext) {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        if (servletContext != null) {
            cookieSerializer.setUseBase64Encoding(false);
        }
        return cookieSerializer;
    }
}
