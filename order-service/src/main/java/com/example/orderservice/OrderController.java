package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducer orderProducer;
    private final OrderServiceImpl orderService;
//    @GetMapping
//    public ResponseEntity<String> sendEvent(@RequestParam(defaultValue = "hunghx") String name){
//
//        orderProducer.sendOrderEvent(UUID.randomUUID().toString(),name, name+"@gmail.com", BigDecimal.valueOf(100000));
//        return ResponseEntity.ok("Success");
//    }
    @GetMapping("/orders/{id}")
    public OrderEvent getOrderById(@PathVariable String id){
        return orderService.findById(id);
    }
}
