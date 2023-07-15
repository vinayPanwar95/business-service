package com.learning.businessservice.mapper;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderApiMapper {

    private final CreateOrderRequestMapper createOrderRequestMapper;

    public OrderActionRequest toCreateFirstVersionActionRequest(OrderCreateRequestDTO createRequestDTO) throws Exception {
        return createOrderRequestMapper.toCreateFirstVersionActionRequest(createRequestDTO);
    }
}
