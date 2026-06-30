package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendOrderEvent(String orderId, String customerName, String customerEmail, BigDecimal totalAmount){
//        OrderEvent orderEvent = new OrderEvent();
//        orderEvent.setOrderId(orderId);
//        orderEvent.setCustomerName(customerName);
//        orderEvent.setCustomerEmail(customerEmail);
//        orderEvent.setTotalAmount(totalAmount);
        log.info("Sending order event to Kafka with orderId :  {}", orderId);
        kafkaTemplate.send("order-events-topic",orderId, customerName);
        log.info("Send event successfully !!!!");
    }
}
