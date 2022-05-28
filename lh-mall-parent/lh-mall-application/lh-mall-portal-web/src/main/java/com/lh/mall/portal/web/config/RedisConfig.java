package com.lh.mall.portal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean//定义第三方的Bean
    //方法设置为公有的，要把哪个对象装配到容器当中，那么就返回这个对象，这边返回的RedisTemplate
    //用Template访问数据库，Template要有访问数据库的能力，那他就得能够创建连接，连接由连接工厂创建，所以就要把连接工厂
    //工厂放进来，注入给Template，他才能访问数据库(RedisConnectionFactory factory)就是声明连接工厂
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        //实例化这个Bean
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //把工厂设置给Template
        template.setConnectionFactory(factory);
        //配置Template主要配置序列化的方式，因为写的是java程序，得到的是java类型的数据，最终要这个数据存储到数据库里面
        //就要指定一种序列化的方式，或者说数据转换的方式
        //设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //设置value的序列化方式
        template.setValueSerializer(RedisSerializer.json());
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        template.afterPropertiesSet();//使上面参数生效
        return template;
    }
}