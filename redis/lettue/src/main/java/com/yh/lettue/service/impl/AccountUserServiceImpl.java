package com.yh.lettue.service.impl;

import com.yh.lettue.model.pojo.AccountUser;
import com.yh.lettue.service.AccountUserService;
import org.springframework.stereotype.Service;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class AccountUserServiceImpl  implements AccountUserService {


    @Override
    public void createUser(AccountUser accountUser) {

    }

    @Override
    public AccountUser queryUserById(Long id) {
        return null;
    }
}