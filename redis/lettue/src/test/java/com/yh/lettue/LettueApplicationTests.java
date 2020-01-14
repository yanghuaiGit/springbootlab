package com.yh.lettue;

import com.yh.lettue.component.RedisManager;
import com.yh.lettue.model.vo.AccountCacheUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class LettueApplicationTests {

    @Resource
    private RedisTemplate<String, AccountCacheUser> redisTemplate;

    @Resource
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    @Resource
    private RedisManager redisManager;


    @Test
    void contextLoads() {
        AccountCacheUser build = AccountCacheUser.builder().id(2L).username("redis").type(1).typeDesc("desc").build();
        redisTemplate.opsForValue().set("1", AccountCacheUser.builder().id(1L).username("redis").type(1).typeDesc("desc").build());
        redisTemplate.execute((RedisCallback<Boolean>) redisConnection ->
                redisConnection.set("2".getBytes(), jackson2JsonRedisSerializer.serialize(build), Expiration.seconds(5000),
                        RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    @Test
    void get() {
        System.out.println(redisTemplate.opsForValue().get("1"));
    }

    @Test
    void redisOpt() {
        log.info("是否存在{}", redisManager.hset("user1", "name", "yanghhuai"));
        log.info("是否存在{}", redisManager.hset("user1", "sex", "1"));
        log.info("是否存在{}", redisManager.hset("user2", "name", "wuyan"));
    }
}
