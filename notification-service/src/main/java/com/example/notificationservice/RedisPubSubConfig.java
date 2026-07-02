package com.example.notificationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisPubSubConfig {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter adapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);


        // Đăng kí kệnh
        container.addMessageListener(adapter, new PatternTopic("order-chanel"));
        return container;
    }

    @Bean
    public MessageListenerAdapter adapter(NotificationReceiver receiver){
        return  new MessageListenerAdapter(receiver,"handlerNotificationAfterCreateOrder");
    }
}
