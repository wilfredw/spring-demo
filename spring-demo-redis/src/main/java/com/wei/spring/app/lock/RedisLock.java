package com.wei.spring.app.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by viruser on 2019/12/7.
 */
public class RedisLock {
    private RedisTemplate redisTemplate;
    public RedisLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void lock() {
        ;
    }

    public void unlock() {
        ;
    }

    private void refresh() {
        ;
    }
}
