package com.springboot.shoehome.domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Table(name = "deposit_order")
@EqualsAndHashCode(callSuper = false)
public class DepositOrder extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 8765786020580817522L;

    @Column private Customer customer; //关联customer
    @Column private String note;
    @Column private double receiptedMoney; //收入金额
    @Column private double presentedMoney; //赠送金额
    @Column private double totalMoney; //总共金额
    @Column private String changeDiscount; //更改的折扣
    @Column private boolean isChangedDiscount; //是否更改折扣
}
