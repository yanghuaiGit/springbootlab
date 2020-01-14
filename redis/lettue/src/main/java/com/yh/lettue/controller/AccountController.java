package com.yh.lettue.controller;

import com.yh.lettue.model.pojo.AccountUser;
import com.yh.lettue.model.vo.AccountCacheUser;
import com.yh.lettue.service.AccountUserService;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
public class AccountController {

    @Resource
    private AccountUserService accountUserService;

    @Resource
    private RedisTemplate<String, AccountCacheUser> redisTemplate;


    @PostMapping
    public void createAccount(@RequestBody AccountUser accountUser) {
        accountUserService.createUser(accountUser);
    }

    @GetMapping("/{id}")
    public AccountUser queryById(@PathVariable Long id) {
        return accountUserService.queryUserById(id);
    }

    @GetMapping("/queryByName")
    public List<AccountCacheUser> queryByName(@RequestParam String name) {
        return accountUserService.queryByName(name);
    }


    @GetMapping("/test")
    public void test() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        AccountCacheUser build = AccountCacheUser.builder().id(2L).username("redis").type(1).typeDesc("desc").build();
        redisTemplate.opsForValue().set("1", AccountCacheUser.builder().id(1L).username("redis").type(1).typeDesc("desc").build());
        redisTemplate.execute((RedisCallback<Boolean>) redisConnection ->
                redisConnection.set("2".getBytes(), jackson2JsonRedisSerializer.serialize(build), Expiration.seconds(5000),
                        RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    @GetMapping("/get")
    public AccountCacheUser get() {
        return redisTemplate.opsForValue().get("1");
    }


}
