package com.example.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationReceiver {
    public void handlerNotificationAfterCreateOrder(String message){
        log.info("Notification service nhận và x lý event ");
        log.info("MESSAGE : {}",message);
        log.info("Tiến hành gửi email đặt hangf thành công");
    }

}
