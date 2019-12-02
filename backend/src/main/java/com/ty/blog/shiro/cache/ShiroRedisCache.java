package com.ty.blog.shiro.cache;

import com.ty.blog.constant.SecurityConsts;
import com.ty.blog.redis.RedisUtil;
import com.ty.blog.shiro.jwt.JwtConfig;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * @ClassName: ShiroRedisCache
 * @Description: 自定义shiro的redis缓存
 * @author zhangtainyi
 * @date 2019/9/10 14:43
 *
 */
@Component
public class ShiroRedisCache<K,V> implements Cache<K,V> {


    @Autowired
    private RedisUtil redisUtil;

    /**
     * shiro缓存前缀
     */
    private final String CACHE_PREFIX = SecurityConsts.PREFIX_SHIRO_CACHE;
    // 超时时间设置(分钟)
//    public static final int DEFAULT_EXPIRE = 30;
    /**
     * 常量表示一分钟转成60秒
     */
    public static final int SECOND = 60;
    /**
     * 过期时间，（分钟）
     */
    private int expire;

    /**
     * 出事haul过期时间
     * @param jwtConfig jwt配置文件对象
     */
    @Autowired
    public ShiroRedisCache(JwtConfig jwtConfig) {
        this.expire = jwtConfig.getRefreshTokenExpireTime() * SECOND;
    }

    private String getKey(K k){

        return CACHE_PREFIX + k;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("从redis获取数据");
        Object value = redisUtil.get(getKey(k));
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

    public V put(K k, V v, long expire) throws CacheException {
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
            values.add((V) value);
        }
        return values;
    }
}