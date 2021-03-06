package com.yh.lettue.model.pojo;

import com.yh.lettue.enmus.AccountTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 */

@Data
public class AccountUser implements Serializable {
    /**
     * 账号id
     */
    private Long id;
    /**
     * 账户类型 0:员工,1:用户
     */
    private AccountTypeEnum type;

    /**
     * 账户来源 0:skr电商,1:sky新闻客户端
     */
    private Integer orign;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 创建ip
     */
    private Long createIpAt;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginAt;
    /**
     * 最后一次登录ip
     */
    private String last_login_ip_at;
    /**
     * 登录次数
     */
    private Long loginTimes;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    private Integer status;
}
