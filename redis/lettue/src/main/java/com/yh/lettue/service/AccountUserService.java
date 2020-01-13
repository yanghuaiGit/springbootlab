package com.yh.lettue.service;

import com.yh.lettue.model.pojo.AccountUser;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface AccountUserService {

    void createUser(AccountUser accountUser);

    AccountUser queryUserById(Long id);

}
