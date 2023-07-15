package com.learning.businessservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderCreateRequestDTO {
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("orderValue")
    private Integer orderValue;
    @JsonProperty("orderType")
    private OrderTypeDTO orderType;
    @JsonProperty("paymentMode")
    private PaymentModeDTO paymentMode;

    @JsonProperty("submitForApproval")
    private Boolean submitForApproval = false;

    @JsonProperty("requestedBy")
    private Boolean requestedBy = false;




}
