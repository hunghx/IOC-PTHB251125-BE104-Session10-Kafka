package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl {
    private final RedisTemplate<String, OrderEvent> redisTemplate;
    private static final String CACHE_PREFIX_ORDER = "OrderEvent:";
    private final ObjectMapper objectMapper;

//    public OrderEvent findById(String id){
//        // Bước 1 : check trong redis theo orderid : hducudvhd => key OrderEvent:hducudvhd
////        Object obj = redisTemplate.opsForValue().get(CACHE_PREFIX_ORDER+id);
////        OrderEvent oe = objectMapper.convertValue(obj, OrderEvent.class);
//
//        OrderEvent oe = redisTemplate.opsForValue().get(CACHE_PREFIX_ORDER+id);
//        if(oe!=null){
//            // trong redis đã có dữ liệu này rồi
//            log.info("Trong redis đã có dữ liệu này rồi =>>>>>>>> {}", id);
//            return oe;
//        }
//
//        // Bước 2 ko tìm thấy
//        // Lâ dữ liệu thử công từ db
//        log.info("Trong Redis ko có dữ liệu này =>>>>>> chọc qua Database để lấy");
//        OrderEvent ode = new OrderEvent(id,"Hung hx"+id,"hunghx@gmail.com", LocalDateTime.now(), BigDecimal.valueOf(1000));
//        // Lưu vao redis
//        redisTemplate.opsForValue().set(CACHE_PREFIX_ORDER+id, ode, Duration.of(10, ChronoUnit.MINUTES));
//        return ode;
//    }
    @Cacheable(key = "#id", value = "order_item_cache")
    public OrderEvent findById(String id){
        log.info("Dữ liệu chưa có trong redis =>> đọc từ Database ");
        return new OrderEvent(id,"Hung hx"+id,"hunghx@gmail.com", LocalDateTime.now(), BigDecimal.valueOf(1000));
    }
}
