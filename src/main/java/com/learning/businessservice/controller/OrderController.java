package com.learning.businessservice.controller;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.dto.OrderResponseDTO;
import com.learning.businessservice.entity.OrderEntity;
//import com.learning.businessservice.kafka.service.KafkaListenerService;
import com.learning.businessservice.kafka.service.KafkaProducerService;
import com.learning.businessservice.mapper.OrderApiMapper;
import com.learning.businessservice.service.OrderService;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderApiMapper orderApiMapper;


    private final ApplicationEventPublisher eventPublisher;
    @PostMapping("order")
    private ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderCreateRequestDTO createRequestDTO){
        log.info("handing business creation");
        System.err.println("createRequestDTO : "+ createRequestDTO);
        try {
            OrderActionRequest actionRequest = orderApiMapper.toCreateFirstVersionActionRequest(createRequestDTO);
            OrderEntity order = orderService.createNewOrder(actionRequest);
            eventPublisher.publishEvent(order);
            return ResponseEntity.status(HttpStatus.OK).body(orderApiMapper.toOrderResponseDTO(order));
        } catch (Exception e) {
            System.err.println(" inside catch");
            throw new RuntimeException(e);
        }
    }
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("postToKafka")
    private void postToKafka(@RequestBody OrderCreateRequestDTO createRequestDTO){
        kafkaProducerService.sendMsgViaKafkaTemplate("input" , "orderId", "orderNumber");
    }



}
