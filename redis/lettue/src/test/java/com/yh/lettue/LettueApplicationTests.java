package com.yh.lettue;

import com.yh.lettue.model.vo.AccountCacheUser;
import com.yh.lettue.service.AccountUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import javax.annotation.Resource;

@SpringBootTest
class LettueApplicationTests {

    @Resource
    private RedisTemplate<String, AccountCacheUser> redisTemplate;

    @Test
    void contextLoads() {

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        AccountCacheUser build = AccountCacheUser.builder().id(2L).username("redis").type(1).typeDesc("desc").build();
        redisTemplate.opsForValue().set("1", AccountCacheUser.builder().id(1L).username("redis").type(1).typeDesc("desc").build());
        redisTemplate.execute((RedisCallback<Boolean>) redisConnection ->
                redisConnection.set("2".getBytes(), genericJackson2JsonRedisSerializer.serialize(build), Expiration.seconds(5000),
                        RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    @Test
    void get() {
        System.out.println(redisTemplate.opsForValue().get("1"));
    }
}
