package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final StringRedisTemplate redisTemplate;
    private final OrderProducer orderProducer;
    private final OrderServiceImpl orderService;
    @GetMapping
    public ResponseEntity<String> sendEvent(@RequestParam(defaultValue = "hunghx") String name){
        // Cấu hình Producer / Pubblisher : người gửi tin
        log.info("Bắt đầu tạo đơn hàng với customername : {}", name);
        String orderId = UUID.randomUUID().toString();

        String message =  "ORDER_ID :"+orderId + "| Customer Name : "+name;
        log.info("Order service phát tin lên Redis ....");
        redisTemplate.convertAndSend("order-chanel", message);

//        orderProducer.sendOrderEvent(UUID.randomUUID().toString(),name, name+"@gmail.com", BigDecimal.valueOf(100000));
        return ResponseEntity.ok("Success");
    }
//    @GetMapping("/orders/{id}")
//    public OrderEvent getOrderById(@PathVariable String id){
//        return orderService.findById(id);
//    }
}
