package com.yh.lettue.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@Builder
@RedisHash(value = "account-user", timeToLive = 60)
@AllArgsConstructor
@NoArgsConstructor
public class AccountCacheUser {
    /**
     * 账号id
     */
    @Id
    private Long id;
    @Indexed
    private String username;
    /**
     * 账户类型 0:员工,1:用户
     */
    private Integer type;
    /**
     * 账户类型 0:员工,1:用户
     */
    private String typeDesc;
}
