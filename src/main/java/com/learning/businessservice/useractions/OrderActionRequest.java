package com.learning.businessservice.useractions;

import com.learning.businessservice.model.OrderType;
import com.learning.businessservice.model.PaymentMode;
import com.learning.commonlbs.useractions.ActionRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.ObjectProvider;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Accessors(fluent = true)
public class OrderActionRequest extends ActionRequest {
    private String comment;
    private OrderType orderType;
    private Integer orderValue;
    private PaymentMode paymentMode;



}
