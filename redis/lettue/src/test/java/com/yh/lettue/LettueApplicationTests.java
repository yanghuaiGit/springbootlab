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
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class LettueApplicationTests {

    @Resource
    private RedisTemplate<String, AccountCacheUser> redisTemplate;

    @Resource
    private RedisTemplate<String, String> stringStringRedisTemplate;


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

        //获取不存在的key 获取的值为null
        System.out.println(redisTemplate.opsForValue().get("123444"));
    }

    @Test
    void redisOpt() {
        stringStringRedisTemplate.opsForZSet().add("12", "123", 123);
        stringStringRedisTemplate.opsForZSet().add("12", "1223", 123);

        stringStringRedisTemplate.opsForZSet().add("122", "12233", 123);
        stringStringRedisTemplate.opsForZSet().add("122", "1223", 1223);

        //将12 和 122两个集合取交集 其分数值为2个集合对应的分数值之和
        stringStringRedisTemplate.opsForZSet().intersectAndStore("12", "122", "2");

        stringStringRedisTemplate.opsForValue().set("ueser:123:2344", "233");
        stringStringRedisTemplate.opsForValue().set("ueser2::123:2344", "233");


        log.info("是否存在{}", redisManager.hset("user1", "name", "yanghhuai"));
        log.info("是否存在{}", redisManager.hset("user1", "sex", "1"));
        log.info("是否存在{}", redisManager.hset("user2", "name", "wuyan"));
    }

    @Test
    void incre() {
        log.info("对不存在的值进行自增{}", stringStringRedisTemplate.opsForValue().increment("incre"));
        log.info("对不存在的值进行自增{}", stringStringRedisTemplate.opsForValue().increment("incre1", 22));

        log.info("对不存在的值进行自减{}", stringStringRedisTemplate.opsForValue().decrement("incre2"));
        log.info("对不存在的值进行自减{}", stringStringRedisTemplate.opsForValue().decrement("incre22", 22));
    }

    @Test
    void expirOpt() {
        //key不存在 返回的值是false 不会设值  只有key存在 才会进行更新
        Boolean s1 = stringStringRedisTemplate.opsForValue().setIfPresent("s1", "2");
        log.info("对不存在的key进行 setIfPresent{}", s1);

        stringStringRedisTemplate.opsForValue().set("time", "1", 100L, TimeUnit.MINUTES);

        //会将过期时间重置为-1
        s1 = stringStringRedisTemplate.opsForValue().setIfPresent("time", "2");
        log.info("对已存在的key进行 setIfPresent{}", s1);
    }
}
