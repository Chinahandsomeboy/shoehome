package com.springboot.shoehome.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.JoinType;

/**
 * @author zn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryJoinFilter {
    private String table;
    private JoinType joinType;

    public static QueryJoinFilter leftJoin(String table){
        return new QueryJoinFilter(table, JoinType.LEFT);
    }

    public static QueryJoinFilter rightJoin(String table){
        return new QueryJoinFilter(table, JoinType.RIGHT);
    }

    public static QueryJoinFilter innerJoin(String table){
        return new QueryJoinFilter(table, JoinType.INNER);
    }
}
