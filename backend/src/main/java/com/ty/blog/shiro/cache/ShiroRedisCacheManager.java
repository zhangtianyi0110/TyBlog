package com.ty.blog.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @ClassName: ShiroRedisCacheManager
 * @Description: 自定义shiro缓存管理
 * @author zhangtainyi
 * @date 2019/9/10 14:43
 *
 */
@Component
public class ShiroRedisCacheManager<K,V> implements CacheManager {
    @Autowired
    private ShiroRedisCache<K,V> shiroRedisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return (Cache<K, V>) shiroRedisCache;
    }
}