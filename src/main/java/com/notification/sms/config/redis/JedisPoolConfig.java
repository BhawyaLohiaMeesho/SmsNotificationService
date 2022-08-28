package com.notification.sms.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisPoolConfig {
    @Bean
    public JedisPool jedisPool(){
        return new JedisPool();
    }
}
