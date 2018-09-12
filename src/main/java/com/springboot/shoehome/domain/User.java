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
@Table(name = "[user]")
@EqualsAndHashCode(callSuper = false)
public class User extends AbsEntity implements Serializable {
    private static final long serialVersionUID = 4229235295213917883L;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String role;
}
