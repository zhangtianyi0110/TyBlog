package com.ty.blog.shiro.jwt;

import com.ty.blog.util.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
//@DependsOn({"jwtConfig"})
public class JwtRedisCache<K,V> implements Cache<K,V> {

    @Autowired
    private RedisUtil redisUtil;
    private final JwtConfig jwtConfig;

    private final String CACHE_PREFIX = "jwt:";//定义键前缀
    // 超时时间设置(分钟)
//    public static final int DEFAULT_EXPIRE = 30;
    public static final int SECOND = 60;
    private int expire;

    @Autowired
    public JwtRedisCache(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.expire = jwtConfig.getRefreshTokenExpireTime() * SECOND;
    }

    private String getKey(K k){
        return CACHE_PREFIX + k;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("从redis获取数据");
        Object value = (Object ) redisUtil.get(getKey(k));
        if(value != null){
            //在redis获取后可以存放在本地缓存中进一步提高性能，减轻redis压力
            return (V) value;
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        String key = getKey(k);
        Object value = v;
        redisUtil.set(key,value);
        redisUtil.expire(key,expire);
        return v;
    }

    /**
     *
     * @param k
     * @param v
     * @param expire 分钟 int 失效时间
     * @return
     * @throws CacheException
     */
    public V put(K k, V v, Integer expire) throws CacheException {
        String key = getKey(k);
        Object value = v;
        redisUtil.set(key,value);
        redisUtil.expire(key,expire * SECOND);
        return v;
    }


    @Override
    public V remove(K k) throws CacheException {
        String key = getKey(k);
        Object value = redisUtil.get(key);
        redisUtil.del(key);
        if(value != null){
            return (V) value;
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        //获取RedisCache所有键
        Set<String> keys = redisUtil.keys(CACHE_PREFIX);
        if(CollectionUtils.isEmpty(keys)){
            for (String key : keys) {
                redisUtil.del(key);
            }
        }
    }

    @Override
    public int size() {
        return redisUtil.keys(CACHE_PREFIX).size();
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<>();
        for (String key : redisUtil.keys(CACHE_PREFIX)) {
            keys.add((K) key);
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Set<V> values = new HashSet<>();
        for (Object value : redisUtil.values(CACHE_PREFIX)) {
            values.add((V)value);
        }
        return values;
    }
}