package com.springboot.shoehome.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author zn
 */

@Entity
@Data
@Table(name = "item")
@EqualsAndHashCode(callSuper = false)
public class  Item extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 5216977460866365051L;

    @Column
    private String name;

    @JoinColumn(name = "itemLargeType")
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ItemLargeType itemLargeType;

    @JoinColumn(name = "itemSmallType")
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ItemSmallType itemSmallType;

    @Column
    private double price;
}
