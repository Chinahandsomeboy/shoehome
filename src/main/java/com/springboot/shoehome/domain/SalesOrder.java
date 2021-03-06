package com.springboot.shoehome.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.shoehome.enums.OrderStatusType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @ManyToOne
    //@ManyToOne(fetch= FetchType.LAZY)
    //@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Customer customer;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectDate; //期望交付日期

    @Column
    @Enumerated(EnumType.STRING)
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
    @Type(type = "true_false")
    private Boolean isModifiedPrice;


}
