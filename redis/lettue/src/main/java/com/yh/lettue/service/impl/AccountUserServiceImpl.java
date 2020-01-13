package com.yh.lettue.service.impl;

import com.yh.lettue.model.pojo.AccountUser;
import com.yh.lettue.model.vo.AccountCacheUser;
import com.yh.lettue.repo.AccountUserCacheRepository;
import com.yh.lettue.repo.AccountUserMapper;
import com.yh.lettue.service.AccountUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Resource
    private AccountUserMapper accountUserMapper;

    @Resource
    private AccountUserCacheRepository accountUserCacheRepository;

    @Override
    public void createUser(AccountUser accountUser) {
        accountUserMapper.createUser(accountUser);
    }

    @Override
    public AccountUser queryUserById(Long id) {
        return accountUserMapper.queryUserById(id);
    }

    @Override
    public List<AccountCacheUser> queryByName(String name) {
        Optional<List<AccountCacheUser>> cached = accountUserCacheRepository.findByUsername(name);
        if (cached.isPresent() && !CollectionUtils.isEmpty(cached.get())) {
            return cached.get();
        } else {
            List<AccountUser> accountUsers = accountUserMapper.queryByName(name);
            if (!CollectionUtils.isEmpty(accountUsers)) {
                List<AccountCacheUser> data = new ArrayList<>(accountUsers.size());
                accountUsers.forEach(u -> {
                    AccountCacheUser build = AccountCacheUser.builder()
                            .id(u.getId())
                            .username(u.getUsername())
                            .type(u.getType().getCode())
                            .typeDesc(u.getType().getDesc())
                            .build();
                    data.add(build);
                });
                accountUserCacheRepository.saveAll(data);
                return data;
            }
        }
        return new ArrayList<>(2);
    }
}
