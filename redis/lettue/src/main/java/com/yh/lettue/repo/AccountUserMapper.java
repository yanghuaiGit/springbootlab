package com.yh.lettue.repo;


import com.yh.lettue.model.pojo.AccountUser;
import org.springframework.stereotype.Repository;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */

@Repository
public interface AccountUserMapper {

    void createUser(AccountUser accountUser);

    AccountUser queryUserById(Long id);
}
