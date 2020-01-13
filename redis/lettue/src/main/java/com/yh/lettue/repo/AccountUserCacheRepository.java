package com.yh.lettue.repo;

import com.yh.lettue.model.vo.AccountCacheUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountUserCacheRepository extends CrudRepository<AccountCacheUser, Long> {
    Optional<List<AccountCacheUser>> findByUsername(String name);
}
