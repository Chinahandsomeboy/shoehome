package com.springboot.shoehome.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

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
