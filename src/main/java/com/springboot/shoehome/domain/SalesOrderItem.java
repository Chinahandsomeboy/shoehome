package com.springboot.shoehome.domain;

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
@Table(name = "public.sales_order_item")
public class SalesOrderItem extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 4439487453280161403L;

    @Column private String salesOrderId;//绑定salessorderid
    @Column private Item item; //项目
    @Column private int quantity;
}
