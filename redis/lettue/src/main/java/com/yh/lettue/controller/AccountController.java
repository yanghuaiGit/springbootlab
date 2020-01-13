package com.yh.lettue.controller;

import com.yh.lettue.model.pojo.AccountUser;
import com.yh.lettue.service.AccountUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping
    public void createAccount(@RequestBody AccountUser accountUser) {
        accountUserService.createUser(accountUser);
    }

    @GetMapping("/{id}")
    public AccountUser queryById(@PathVariable Long id) {
        return accountUserService.queryUserById(id);
    }
}
