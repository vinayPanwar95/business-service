package com.learning.businessservice.mapper;

import com.learning.businessservice.dto.OrderCreateRequestDTO;
import com.learning.businessservice.model.OrderType;
import com.learning.businessservice.model.PaymentMode;
import com.learning.businessservice.useractions.OrderActionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.learning.common.validator.OrderValidator.validateNotNUll;

@Component
@RequiredArgsConstructor
public class CreateOrderRequestMapper {
    public OrderActionRequest toCreateFirstVersionActionRequest(OrderCreateRequestDTO requestDTO) throws Exception {
        validateNotNUll(requestDTO.getOrderType());
        validateNotNUll(requestDTO.getOrderValue());
        validateNotNUll(requestDTO.getPaymentMode());

        return OrderActionRequest.builder()
                .comment(requestDTO.getComment())
                .orderType(OrderType.fromString(requestDTO.getOrderType().name()).get())
                .orderValue(requestDTO.getOrderValue())
                .paymentMode(PaymentMode.fromString(requestDTO.getPaymentMode().name()).get())
                                            .build();

    }
}
