package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
    @Autowired
    private ObjectMapper objectMapper;
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJacksonJsonRedisSerializer(objectMapper));
//        return redisTemplate;
//    }
@Bean
public RedisTemplate<String, OrderEvent> productRedisTemplate(
        RedisConnectionFactory connectionFactory
) {
    RedisTemplate<String, OrderEvent> template = new RedisTemplate<>();

    template.setConnectionFactory(connectionFactory);

    JacksonJsonRedisSerializer<OrderEvent> serializer =
            new JacksonJsonRedisSerializer<>(OrderEvent.class);

    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(serializer);

    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(serializer);

    template.afterPropertiesSet();

    // OTP
    // token : accessToken, RefreshToken, blackList



    return template;
}
}
