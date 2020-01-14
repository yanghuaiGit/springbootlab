package com.yh.lettue.config.redis;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 */
@Configuration
public class RedisConfig {
    @Resource
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }

    /**
     * 优先读主节点  还可以配置优先读从
     *
     * @return redis定制
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

//    /**
//     * redis也可以配置类型转换器
//     */
//    @Bean
//    public RedisCustomConversions redisCustomConversions() {
//        return new RedisCustomConversions(
//                Arrays.asList(new Mo.
//                neyToBytesConverter(), new BytesToMoneyConverter()));
//    }
}
