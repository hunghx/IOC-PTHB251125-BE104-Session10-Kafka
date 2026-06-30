package com.example.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

    // tạo phương thức lắng nghe event
    @KafkaListener(topics = "order-events-topic" , groupId = "notification-group")
    public void consumerOrderEvent(String value){
        log.info("Receiving Dataa .......");
        log.info("Logging Info : Customer Name = {}", value);

        log.info("Handle other action .....");
    }
}
