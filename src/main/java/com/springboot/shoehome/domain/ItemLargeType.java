package com.springboot.shoehome.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author zn
 */

@Entity
@Data
@Table(name ="item_largeType")
@EqualsAndHashCode(callSuper = false)
public class ItemLargeType extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 6631071233113835862L;

    @Column
    private String name;
}
