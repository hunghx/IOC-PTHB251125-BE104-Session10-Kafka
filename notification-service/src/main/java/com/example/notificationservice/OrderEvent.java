package com.example.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEvent {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private LocalDateTime createdAt = LocalDateTime.now();
    private BigDecimal totalAmount;
}
