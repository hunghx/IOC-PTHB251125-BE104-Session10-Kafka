package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendOrderEvent(String orderId, String customerName, String customerEmail, BigDecimal totalAmount){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(orderId);
        orderEvent.setCustomerName(customerName);
        orderEvent.setCustomerEmail(customerEmail);
        orderEvent.setTotalAmount(totalAmount);
        log.info("Sending order event to Kafka with orderId :  {}", orderId);
        // Khi dùng producer để gửi 1 Object thì nó còn gửi thêm header chưa tên package
        // Chuyển đổi từ Object và String
//        ObjectMapper objectMapper = new ObjectMapper();
        String stringData = objectMapper.writeValueAsString(orderEvent);

        kafkaTemplate.send("order-events-topic",orderId, stringData);
        log.info("Send event successfully !!!!");
    }
}
