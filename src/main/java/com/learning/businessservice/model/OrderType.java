package com.learning.businessservice.model;

import java.util.Arrays;
import java.util.Optional;

public enum OrderType {
    ONLINE,
    OFFLINE;

    public static Optional<OrderType> fromString(String type){
        return Arrays.stream(values()).filter(orderType -> orderType.name().equals(type)).findAny();
    }
}
