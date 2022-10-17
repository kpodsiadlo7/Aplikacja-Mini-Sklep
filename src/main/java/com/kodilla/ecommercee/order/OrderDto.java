package com.kodilla.ecommercee.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long cartId;
    private Long userId;
    private LocalDateTime dateOfOrder;
    private LocalDateTime shippingDate;
    private String shippingAddress;
    private String shippingStatus;
}
