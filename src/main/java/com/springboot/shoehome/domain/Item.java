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

@Entity
@Data
@Table(name = "public.item")
public class Item extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 5216977460866365051L;

    @Column private String name;
    @Column private ItemLargeType largeType; //商品大类别
    @Column private ItemSmallType smallType; //商品小类别
    @Column private double price;
}
