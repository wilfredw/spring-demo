package com.wei.spring.app.session;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * Created by viruser on 2019/7/30.
 */
@EnableRedisWebSession
public class SessionConfiguration {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}