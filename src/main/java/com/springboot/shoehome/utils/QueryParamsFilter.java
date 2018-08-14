package com.springboot.shoehome.utils;

import com.springboot.shoehome.enums.QueryParamsMatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public static QueryParamsFilter eq(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.EQ);
    }

    public static QueryParamsFilter ne(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.NE);
    }

    public static QueryParamsFilter gt(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.GT);
    }

    public static QueryParamsFilter lt(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.LT);
    }

    public static QueryParamsFilter ge(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.GE);
    }

    public static QueryParamsFilter le(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.LE);
    }

    public static QueryParamsFilter like(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.LIKE);
    }

    public static QueryParamsFilter in(String name, Object valueList){
        return new QueryParamsFilter(name, valueList, QueryParamsMatchType.IN);
    }

    public static QueryParamsFilter isNull(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.ISNULL);
    }

    public static QueryParamsFilter isNotNull(String name, Object value){
        return new QueryParamsFilter(name, value, QueryParamsMatchType.ISNOTNULL);
    }

    public static QueryParamsFilter between(String name, Object minValue, Object maxValue){
        List valueList =new ArrayList();
        valueList.add(minValue);
        valueList.add(maxValue);
        return new QueryParamsFilter(name, valueList, QueryParamsMatchType.BETWEEN);
    }

}
