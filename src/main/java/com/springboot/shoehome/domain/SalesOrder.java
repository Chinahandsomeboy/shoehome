package com.springboot.shoehome.domain;

import com.springboot.shoehome.enums.OrderStatusType;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zn
 */

@Data
@Entity
@Table(name = "public.sales_order")
public class SalesOrder extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 4763619284626943786L;

    @Column private Customer customerId; //关联customer
    @Column private SalesOrderItem salesOrderItem;//关联salesorderitem
    @Column private Date expectDate; //期望交付日期
    @Column private OrderStatusType orderStatus; //订单状态 用enums
    @Column private String note;
    @Column private double totalPrice;
    @Column private double discountPrice;
    @Column private double finalPrice;
    @Column private boolean isModifiedPrice;


}
