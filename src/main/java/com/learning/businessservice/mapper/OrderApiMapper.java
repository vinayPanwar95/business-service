package com.learning.businessservice.mapper;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.dto.OrderResponseDTO;
import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderApiMapper {

    private final CreateOrderRequestMapper createOrderRequestMapper;
    private final OrderMapper orderMapper;

    public OrderActionRequest toCreateFirstVersionActionRequest(OrderCreateRequestDTO createRequestDTO) throws Exception {
        return createOrderRequestMapper.toCreateFirstVersionActionRequest(createRequestDTO);
    }

    public OrderResponseDTO toOrderResponseDTO(OrderEntity order) {
        return orderMapper.toOrderResourceDTO(order);
    }
}
