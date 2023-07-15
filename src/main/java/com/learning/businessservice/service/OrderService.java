package com.learning.businessservice.service;

import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CreateNewOrderService createNewOrderService;
    public OrderEntity createNewOrder(OrderActionRequest actionRequest) {
        return createNewOrderService.createOrder(actionRequest);
    }
}
