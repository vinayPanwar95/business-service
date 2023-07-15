package com.learning.businessservice.dto;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(fluent = true)
public class OrderResponseDTO {

    private String orderId;
    private Integer orderVersion;
    private String status;
    private String orderType   ;
    private Integer orderValue   ;
    private String paymentMode   ;
    private String comment   ;
}
