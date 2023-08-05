package com.learning.businessservice.event;

import com.learning.businessservice.entity.OrderEntity;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderEvent {
    OrderEntity order;
}
