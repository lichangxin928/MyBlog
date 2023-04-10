package com.lcx.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valueOperations;


    private String prefix = "cms:";

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    /**
     * redis 锁前缀
     */
    private static final String LOCK_PRE = "lock_";

    /**
     * 插入缓存默认时间
     *
     * @param key   键
     * @param value 值
     * @author zmr
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 插入缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间(s)
     * @author zmr
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(prefix + key, toJson(value));
        redisTemplate.expire(prefix + key, expire, TimeUnit.SECONDS);
    }

    /**
     * NX-插入缓存默认时间
     *
     * @param key   键
     * @param value 值
     * @author zmr
     */
    public Boolean setIfAbsent(String key, Object value) {
        return setIfAbsent(key, value, DEFAULT_EXPIRE);
    }

    /**
     * NX-插入缓存
     *
     * @param key    键
     * @param value  值
     * @param expire 过期时间(s)
     * @author zmr
     */
    public Boolean setIfAbsent(String key, Object value, long expire) {
        return valueOperations.setIfAbsent(prefix + key, toJson(value), expire, TimeUnit.SECONDS);
    }

    /**
     * 自增
     *
     * @param key
     * @param delta
     * @param expire
     */
    public void incr(String key, long delta, long expire) {
        valueOperations.increment(prefix + key, delta);
        redisTemplate.expire(prefix + key, expire, TimeUnit.SECONDS);
    }

    /**
     * 自增 返回自增后的值
     *
     * @param key
     * @param delta
     * @param expire
     * @return 自增后的值
     */
    public Long incrAndGet(String key, long delta, long expire) {
        Long result = valueOperations.increment(prefix + key, delta);
        redisTemplate.expire(prefix + key, expire, TimeUnit.SECONDS);
        return result;
    }

    /**
     * 修改缓存过期时间
     *
     * @param key    键
     * @param expire 过期时间(s)
     */
    public void expire(String key, long expire) {
        redisTemplate.expire(prefix + key, expire, TimeUnit.SECONDS);
    }

    /**
     * 返回字符串结果
     *
     * @param key 键
     * @return
     * @author zmr
     */
    public String get(String key) {
        return valueOperations.get(prefix + key);
    }

    /**
     * 返回字符串结果
     *
     * @param key 键
     * @return
     * @author zmr
     */
    public String get(String prefix, String key) {
        return valueOperations.get(prefix + key);
    }

    /**
     * 返回指定类型结果
     *
     * @param key   键
     * @param clazz 类型class
     * @return
     * @author zmr
     */
    public <T> T get(String key, Class<T> clazz) {
        String value = valueOperations.get(prefix + key);
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 返回指定类型结果
     *
     * @param key   键
     * @param clazz 类型class
     * @return
     * @author zmr
     */
    public <T> T get(String prefix, String key, Class<T> clazz) {
        String value = valueOperations.get(prefix + key);
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @author zmr
     */
    public void delete(String key) {
        redisTemplate.delete(prefix + key);
    }

    /**
     * 清空缓存
     */
    public void clear() {
        Set<String> keys = redisTemplate.keys(prefix + "*");
        redisTemplate.delete(keys);
    }

    public void clear(String key) {
        Set<String> keys = redisTemplate.keys(prefix + key + "*");
        redisTemplate.delete(keys);
    }

    public void clear(String prefix, String key) {
        Set<String> keys = redisTemplate.keys(prefix + key + "*");
        redisTemplate.delete(keys);
    }

    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    public void lPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(prefix + key, value);
    }

    public void lPush(String prefix, String key, Object value) {
        redisTemplate.opsForList().leftPush(prefix + key, value);
    }

    public Object brPop(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForList().rightPop(prefix + key, timeout, timeUnit);
    }

    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(prefix + key);
    }

    public Object brPop(String prefix, String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForList().rightPop(prefix + key, timeout, timeUnit);
    }

    public Object hget(String key, String filed) {
        return redisTemplate.opsForHash().get(prefix + key, filed);
    }

    public Object hget(String prefix, String key, String filed) {
        return redisTemplate.opsForHash().get(prefix + key, filed);
    }

    public void hset(String key, Map value) {
        redisTemplate.opsForHash().putAll(prefix + key, value);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
                || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }


    /*
    获取键值对
     */
    public Map<Object, Object> entries(String key){
        return redisTemplate.opsForHash().entries(prefix + key);
    }

    public void hset(String key, Map value,long expire) {
        redisTemplate.opsForHash().putAll(prefix + key, value);
        redisTemplate.expire(prefix + key, expire, TimeUnit.SECONDS);
    }
    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


    public Long getIncrNum(String key) {
        return this.incrAndGet(key, 1l, 24 * 60 * 60 );

    }




}