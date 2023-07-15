package com.learning.businessservice.repository;

import com.learning.businessservice.entity.OrderEntity;
import com.learning.businessservice.model.OrderType;
import com.learning.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity> {
    List<OrderEntity> getByOrderTypeOrderByOrderIdAscOrderVersionDesc(OrderType orderType);
}
