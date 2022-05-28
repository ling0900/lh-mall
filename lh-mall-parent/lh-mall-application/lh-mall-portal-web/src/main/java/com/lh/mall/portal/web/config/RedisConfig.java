package com.lh.mall.portal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author 99261
 * 方法设置为公有的，要把哪个对象装配到容器当中，那么就返回这个对象，这边返回的RedisTemplate。
 */
@Configuration
public class RedisConfig {

    @Bean
    /**
     redisTemplate要有访问redis数据库的能力，需要创建连接（工厂）注入给redisTemplate，
     RedisConnectionFactory factory：声明连接工厂。
    */
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        // 实例化这个Bean
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 把工厂设置给Template
        redisTemplate.setConnectionFactory(factory);
        /*
        主要配置序列化的方式，得到的是java类型的数据和数据存储数据做交互，故需要指定序列化方式（数据转换的方式）。
        */
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        // 使配置生效
        redisTemplate.afterPropertiesSet();
        // 返回
        return redisTemplate;
    }
}