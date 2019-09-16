package com.example.redis.utils;

/**
 * Created by yufengwang213979 on 2018/4/4.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
@Component
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void add(String key,String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public void add(String key,String value,Long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean update(String key, String value) {
        return redisTemplate.opsForValue().setIfPresent(key, value);
    }

    public boolean delete(String key) {
        return redisTemplate.opsForValue().getOperations().delete(key);
    }
}
