package com.yh.lettue.config.redis;

import com.yh.lettue.model.pojo.User;
import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 优先读主节点  还可以配置优先读从
     * @return redis定制
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }
}
