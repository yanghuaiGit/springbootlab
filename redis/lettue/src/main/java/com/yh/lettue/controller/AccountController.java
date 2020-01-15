package com.yh.lettue.controller;

import com.yh.lettue.model.pojo.AccountUser;
import com.yh.lettue.model.vo.AccountCacheUser;
import com.yh.lettue.service.AccountUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 */
@RestController
public class AccountController {

    @Resource
    private AccountUserService accountUserService;


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

}
