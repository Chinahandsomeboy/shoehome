package com.springboot.shoehome.utils;

import com.springboot.shoehome.enums.QueryParamsMatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author acer
 * @date 2018/7/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class QueryParamsFilter {

    private String name;
    private Object value;
    private QueryParamsMatchType type;

}
