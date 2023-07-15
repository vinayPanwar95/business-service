package com.learning.businessservice.entity;

import com.learning.businessservice.model.OrderType;
import com.learning.businessservice.model.PaymentMode;
import com.learning.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import static org.hibernate.annotations.ResultCheckStyle.COUNT;

@Entity
@Table(name="ORDER")
@SQLDelete(sql = "UPDATE ORDER SET " + BaseEntity.DELETED + "WHERE ID = ? AND VERSION = ?", check = COUNT) // this will change the hard delete to soft delete
@Where(clause = BaseEntity.NOT_DELETED) // this will add to every hsql, where Deleted = false
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {



    @Column(name= "ORDER_TYPE")
    private OrderType orderType;

    @Column(name= "ORDER_VALUE")
    private Integer orderValue;

    @Column(name= "PAYMENT_MODE")
    private PaymentMode paymentMode;

    @Column(name= "COMMENT")
    private String comment;
}
