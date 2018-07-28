package com.springboot.shoehome.domain;

import com.springboot.shoehome.enums.OrderStatusType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zn
 */

@Data
@Entity
@Table(name = "sales_order")
@EqualsAndHashCode(callSuper = false)
public class SalesOrder extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 4763619284626943786L;


    @JoinColumn(name = "customer")
    @ManyToOne(fetch= FetchType.LAZY)
    private Customer customer;

    @Column
    private Date expectDate; //期望交付日期

    @Column
    private OrderStatusType orderStatus; //订单状态 用enums

    @Column
    private String note;

    @Column
    private double totalPrice;

    @Column
    private double discountPrice;

    @Column
    private double finalPrice;

    @Column
    private boolean isModifiedPrice;


}
