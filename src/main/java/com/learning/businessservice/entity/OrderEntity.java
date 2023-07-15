package com.learning.businessservice.entity;

import com.learning.businessservice.model.OrderType;
import com.learning.businessservice.model.PaymentMode;
import com.learning.commonlbs.entity.BaseEntity;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.UUID;

import static org.hibernate.annotations.ResultCheckStyle.COUNT;

@Entity
@Table(name="ORDER")
@SQLDelete(sql = "UPDATE ORDER SET " + BaseEntity.DELETED + "WHERE ID = ? AND VERSION = ?", check = COUNT) // this will change the hard delete to soft delete
@Where(clause = BaseEntity.NOT_DELETED) // this will add to every hsql, where Deleted = false
@SuperBuilder
public class OrderEntity extends BaseEntity {

    @Column(name= "ORDER_ID")
    private UUID orderId;

    @Column(name= "ORDER_TYPE")
    private OrderType orderType;

    @Column(name= "ORDER_VALUE")
    private Integer orderValue;

    @Column(name= "PAYMENT_MODE")
    private PaymentMode paymentMode;

    @Column(name= "COMMENT")
    private String comment;
}
