package com.example.notificationservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;

//    // tạo phương thức lắng nghe event
//    @KafkaListener(topics = "order-events-topic" , groupId = "notification-group")
//    public void consumerOrderEvent(String value){
//
//        log.info("Receiving Dataa .......");
//        log.info("Logging Info : Data = {}", value);
//        OrderEvent orderEvent = objectMapper.readValue(value, OrderEvent.class);
//        log.info("Logging Info : CustomerName = {}", orderEvent.getCustomerName());
//        log.info("Handle other action .....");
//    }
}
