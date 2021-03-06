package com.springboot.shoehome.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * @author zn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderFilter {

    enum Direction{Asc,Desc}

    private String name;
    private QueryOrderFilter.Direction direction = Direction.Desc;



    public static QueryOrderFilter desc(String name) {
        return new QueryOrderFilter(name, Direction.Desc);
    }

    public static QueryOrderFilter asc(String name) {
        return new QueryOrderFilter(name, Direction.Asc);
    }

}
