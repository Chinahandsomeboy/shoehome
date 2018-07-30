package com.springboot.shoehome.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 *
 * @author acer
 * @date 2018/7/30
 */
public  class QueryParams<T> implements Specification<T>{

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

        Path<String> name = root.get("name");
        Path<String> note = root.get("note");
        Predicate p1 = criteriaBuilder.equal(name ,"1");
        Predicate p2 = criteriaBuilder.equal(note ,"1");
        return criteriaBuilder.and(p1, p2);
    }
}
