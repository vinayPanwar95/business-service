package com.learning.businessservice.model;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMode {
    CASH,
    ONLINE;

    public static Optional<PaymentMode> fromString(String pMode){
        return Arrays.stream(values()).filter(paymentMode -> paymentMode.name().equals(pMode)).findAny();
    }
}
