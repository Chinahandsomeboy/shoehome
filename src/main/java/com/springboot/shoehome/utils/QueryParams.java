package com.springboot.shoehome.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 * @date 2018/7/30
 */
public  class QueryParams<T> implements Specification<T>{

    private List<QueryParamsFilter> queryParamsFilterList =new ArrayList<QueryParamsFilter>();

    @Override
    public Specification<T> and(Specification<T> other) {
        return null;
    }

    @Override
    public Specification<T> or(Specification<T> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        System.out.println(this.queryParamsFilterList.size());
        Path<String> name = root.get("name");
        Path<String> note = root.get("note");
        Predicate p1 = criteriaBuilder.equal(name ,"1");
        Predicate p2 = criteriaBuilder.equal(note ,"1");
        return criteriaBuilder.and(p1,p2);
    }

    public List<QueryParamsFilter> getQueryParamsFilterList() {
        return queryParamsFilterList;
    }

    public void setQueryParamsFilterList(QueryParamsFilter queryParamsFilter) {
        this.queryParamsFilterList.add(queryParamsFilter);
    }
}
