package com.springboot.shoehome.domain;
import com.springboot.shoehome.enums.DiscountType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author zn
 */

@Entity
@Data
@Table(name = "customer")
@EqualsAndHashCode(callSuper = false)
public class Customer extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 5122628303419443099L;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private DiscountType discount;//折扣用enums

    @Column
    private double balance; //余额

    @Column
    private String note;

}
