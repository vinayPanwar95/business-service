package com.learning.businessservice.controller;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.mapper.OrderApiMapper;
import com.learning.businessservice.service.OrderService;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
public class BusinessController {

    private final OrderService orderService;
    private final OrderApiMapper orderApiMapper;
    @PostMapping("contract")
    private ResponseEntity<?> createOrder(OrderCreateRequestDTO createRequestDTO){
        log.info("handing business creation");
        try {
            OrderActionRequest actionRequest = orderApiMapper.toCreateFirstVersionActionRequest(createRequestDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OrderEntity order =
    }

}
