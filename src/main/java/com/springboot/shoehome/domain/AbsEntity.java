package com.springboot.shoehome.domain;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zn
 */

@Data
@MappedSuperclass
public abstract class AbsEntity implements Serializable {
    private static final long serialVersionUID = 7324815292231059158L;

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column
    private String code;

    @Column
    private Date createDate;

    @Column
    private Date modificationDate;

}
