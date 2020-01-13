package com.yh.lettue.config.redis;

import com.yh.lettue.model.pojo.AccountUser;
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
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, AccountUser> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, AccountUser> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 优先读主节点  还可以配置优先读从
     *
     * @return redis定制
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

//    /**
//     * redis也可以配置类型转换器
//     */
//    @Bean
//    public RedisCustomConversions redisCustomConversions() {
//        return new RedisCustomConversions(
//                Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
//    }
}
