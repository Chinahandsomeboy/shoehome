package com.springboot.shoehome.enums;

import lombok.Data;

import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.EnumSet;

/**
 * @author zn
 */
public enum DiscountType {

    FiveDiscount("五折", 5),SixDiscount("六折", 6),SevenDiscount("七折", 7),
    EightDiscount("八折", 8),NineDiscount("九折", 9),TenDiscount("无折扣", 10);

    private String name;
    private int discount;

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    DiscountType(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }

    public static EnumSet<DiscountType> getAllDiscount(){
        return EnumSet.allOf(DiscountType.class);
    }

    public static EnumSet<DiscountType> getNo6Discount(){
        return EnumSet.of(DiscountType.FiveDiscount,DiscountType.SevenDiscount,DiscountType.EightDiscount,
                            DiscountType.NineDiscount,DiscountType.TenDiscount);
    }

}