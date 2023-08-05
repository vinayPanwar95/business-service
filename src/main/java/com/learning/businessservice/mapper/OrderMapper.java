package com.learning.businessservice.mapper;

import com.learning.businessservice.dto.OrderResponseDTO;
import com.learning.businessservice.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDTO toOrderResourceDTO(OrderEntity order) {

        return OrderResponseDTO.builder()
                .orderId(order.getOrderId().toString())
                .orderVersion(order.getOrderValue())
                .orderType(order.getOrderType().toString())
                .orderValue(order.getOrderValue())
                .paymentMode(order.getPaymentMode().toString())
                .comment(order.getComment())
                .build();
    }
}
