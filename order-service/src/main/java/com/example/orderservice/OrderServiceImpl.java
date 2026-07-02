package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl {
    private static final List<Product> products ;
    private final RedissonClient redissonClient;
    static {
        products = new ArrayList<>();
        products.add(new Product("P001","Sản Phẩm A",1));
    }
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
//    @Cacheable(key = "#id", value = "order_item_cache")
//    public OrderEvent findById(String id){
//        log.info("Dữ liệu chưa có trong redis =>> đọc từ Database ");
//        return new OrderEvent(id,"Hung hx"+id,"hunghx@gmail.com", LocalDateTime.now(), BigDecimal.valueOf(1000));
//    }

    public void order(String productId){
        // Redis là single Thread

        // SET NX (setup khi ko tồn tại)
        // Khi ThreadA gọi Redis để giữ hàng thì NOT EXIST : chỉ tạo key khi chưa tồn tại
        // GIữ chỗ chỉ trong thời gian chờ thanh toán (5 giây)

        // ThreadB sau khi A thực thi thì được gọi tới redis và khoogn th kết nối được do A đang chếm giữ key này

        String lockKey = "Lock:productId"+productId; // tạo khóa duy nhất cho luồng hiện tại đang xử lí đặt hàng
        RLock lock = redissonClient.getLock(lockKey); // đơn luồng

        // Thử chiếm khóa này trong 5s
        // khóa tồn tại tối đa 10 s
        try {
            boolean isLocked = lock.tryLock(5, 10, TimeUnit.SECONDS);
            if (isLocked){
                System.out.println("Thread name : "+ Thread.currentThread().getName() + "đang chiếm key");
                // Kiêm tra kho -> trừ DB

                // trừ tồn kho
                System.out.println("Mua hàng thành công");
            }else {
                System.out.println("Thất bại : sán phẩm đa hết");
            }
        } catch (InterruptedException e) {
            log.error("ko thể chiếm đc khóa này");
            throw new RuntimeException(e);
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock(); // nhả khóa ra
            }
        }
    }
}
