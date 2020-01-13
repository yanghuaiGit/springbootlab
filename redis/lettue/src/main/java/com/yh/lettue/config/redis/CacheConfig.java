package com.yh.lettue.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfig {
    @Value("${cache.expireTime}")
    // 缓存超时时间
    private int cacheExpireTime;

    /**
     * 配置@Cacheable、@CacheEvict等注解在没有指定Key的情况下，key生成策略
     * 该配置作用于缓存管理器管理的所有缓存
     * 最终生成的key 为 cache类注解指定的cacheNames::类名:方法名#参数值1,参数值2...
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            sb.append("#");
            for (Object obj : params) {
                sb.append(obj.toString());
                sb.append(",");
            }
            return sb.substring(0, sb.length() - 1);
        };
    }


    /**
     * 配置缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        //关键点，spring cache 的注解使用的序列化都从这来，没有这个配置的话使用的jdk自己的序列化，实际上不影响使用，只是打印出来不适合人眼识别
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                // 将 key 序列化成字符串
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 将 value 序列化成 json
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))//value序列化方式
                // 设置缓存过期时间，单位秒
                .entryTtl(Duration.ofSeconds(cacheExpireTime))
                // 不缓存空值
                .disableCachingNullValues();

        return RedisCacheManager.builder(factory)
                .cacheDefaults(cacheConfig)
                .build();
    }


}
