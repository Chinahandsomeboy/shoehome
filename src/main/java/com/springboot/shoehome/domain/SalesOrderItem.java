package com.springboot.shoehome.domain;

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
@Table(name = "sales_order_item")
@EqualsAndHashCode(callSuper = false)
public class SalesOrderItem extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 4439487453280161403L;

    @JoinColumn(name = "sales_order")
    @ManyToOne(fetch= FetchType.LAZY)
    private SalesOrder salesOrder;

    @JoinColumn(name = "item")
    @ManyToOne(fetch= FetchType.LAZY)
    private Item item;

    @Column
    private int quantity;
}
