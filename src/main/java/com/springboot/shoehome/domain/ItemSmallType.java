package com.springboot.shoehome.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author zn
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="item_smallType")
@EqualsAndHashCode(callSuper = false)
public class ItemSmallType extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 6459211915546324935L;

    @Column
    private String name;
}
