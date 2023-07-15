package com.learning.businessservice.controller;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.mapper.OrderApiMapper;
import com.learning.businessservice.service.OrderService;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderApiMapper orderApiMapper;
    @PostMapping("contract")
    private ResponseEntity<?> createOrder(OrderCreateRequestDTO createRequestDTO){
        log.info("handing business creation");
        try {
            OrderActionRequest actionRequest = orderApiMapper.toCreateFirstVersionActionRequest(createRequestDTO);
            OrderEntity order = orderService.createNewOrder(actionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderApiMapper.toOrderResponseDTO(order));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
