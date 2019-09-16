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

@Component
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存键值对
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存键值对并设置多久后过期，单位秒
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 根据键获取对应的值
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 只有当对应的key存在时才更新对应的值
     * 如果key不存在，返回false
     * @param key
     * @param value
     * @return
     */
    public boolean setIfPresent(String key, Object value) {
        return redisTemplate.opsForValue().setIfPresent(key, value);
    }

    public boolean delete(String key) {
        return redisTemplate.opsForValue().getOperations().delete(key);
    }
}
