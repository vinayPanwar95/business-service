package com.learning.businessservice.service;

import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.repository.OrderRepository;
import com.learning.businessservice.useractions.OrderActionRequest;
import com.learning.common.datetime.DateTimerHelper;
import com.learning.common.useractions.create.CreateNewService;
import com.learning.common.uuid.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.learning.common.entity.BaseEntity.NEW_ORDER_VERSION;
import static com.learning.common.validator.OrderValidator.validateNotNUll;

@Component
@RequiredArgsConstructor
public class CreateNewOrderService extends CreateNewService<OrderActionRequest, OrderEntity> {

    private final UUIDGenerator uuidGenerator;

    private final OrderRepository orderRepo;
    private final DateTimerHelper dateTimerHelper;

    @Override
    protected OrderEntity saveOrderVersion(OrderEntity newOrder) {
        return orderRepo.save(newOrder);
    }

    @Override
    protected OrderEntity createOrder(OrderActionRequest actionRequest) {
                OrderEntity order = OrderEntity.builder()
                .orderId(uuidGenerator.generate())
                .orderVersion(NEW_ORDER_VERSION)
                .orderType(actionRequest.orderType())
                .orderValue(actionRequest.orderValue())
                .comment(actionRequest.comment())
                .paymentMode(actionRequest.paymentMode())
                .build();
        order.populateTechnicalFields(actionRequest.contextUser() , dateTimerHelper.getNowInUTCTime() , uuidGenerator.generate());
        return order;
    }

    @Override
    protected List<OrderEntity> getContracts(OrderActionRequest newOrderRequest) {
    List<OrderEntity> existingOrder = orderRepo.getByOrderTypeOrderByOrderIdAscOrderVersionDesc(newOrderRequest.orderType());

        Predicate<OrderEntity> predicate = order -> newOrderRequest.hasSameOrderValue(order.getOrderValue());
        return existingOrder.stream().filter(predicate).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    protected void validateRequest(OrderActionRequest newOrderRequest) {
        validateNotNUll(newOrderRequest , "action request");
    }
}
