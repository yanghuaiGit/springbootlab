package com.yh.lettue.model.pojo;

import lombok.Builder;
import lombok.Data;
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
@RedisHash(value = "user", timeToLive = 60)
public class User {
    @Id
    private Long uid;
    @Indexed
    private String uName;
}
