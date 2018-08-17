package com.springboot.shoehome.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author acer
 * @date 2018/7/30
 */
public class QueryParams<T> implements Specification<T> {

	private List<QueryParamsFilter> andFilters ;
	private List<QueryParamsFilter> orFilters ;

	public void and(QueryParamsFilter... queryParamsFilters) {
		andFilters = Arrays.asList(queryParamsFilters);
	}

	public void or(QueryParamsFilter... queryParamsFilters) {
		orFilters = Arrays.asList(queryParamsFilters);
	}

	public QueryParams leftJoin() {

		return this;
	}

	public QueryParams leftInnerJoin() {

		return this;
	}

	public QueryParams rightJoin() {
		return this;
	}

	public QueryParams rightInnerJoin() {

		return this;
	}

	public void order() {

	}

	public void page() {

	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		//System.out.println(this.queryParamsFilterList.size());
		//conjunction和disjunction root.join
		//Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "1"), criteriaBuilder.equal(root.get("code"), "1"));
		//Predicate p1 = criteriaBuilder.or(criteriaBuilder.equal(root.get("note"), "1"));

		//Path<String> name = root.get("name");
		// Path<String> note = root.get("note");
		//Predicate p1 = criteriaBuilder.equal(name ,"1");
		//Predicate p2 = criteriaBuilder.equal(note ,"1");
		//Predicate predicate = criteriaBuilder.or(and,or);
		if(andFilters != null && orFilters == null){
			return parseFilters(andFilters, criteriaBuilder, root, "and");
		}else if(andFilters == null && orFilters != null ){
			return parseFilters(orFilters, criteriaBuilder, root, "or");
		}else if(andFilters == null && orFilters == null){
			System.out.println("none predicate");
		}else {
			return criteriaBuilder.or(parseFilters(andFilters, criteriaBuilder, root, "and"), parseFilters(orFilters, criteriaBuilder, root, "or"));
		}
		return null;
	}

	public Predicate parseFilters(List<QueryParamsFilter> QueryParams, CriteriaBuilder criteriaBuilder, Root<T> root, String type) {
		Predicate predicate = criteriaBuilder.conjunction();
		if (QueryParams != null){
			for (QueryParamsFilter queryParamsFilter : QueryParams) {

				switch (queryParamsFilter.getType()) {
					case EQ:
						predicate = chooseOrAnd(predicate, criteriaBuilder.equal(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case NE:
						predicate = chooseOrAnd(predicate, predicate = criteriaBuilder.notEqual(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case GT:
						predicate = chooseOrAnd(predicate, criteriaBuilder.gt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LT:
						predicate = chooseOrAnd(predicate, criteriaBuilder.lt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case GE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.ge(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.le(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LIKE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.like(root.get(queryParamsFilter.getName()),(String)queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case IN:
						//predicate = criteriaBuilder.in(root.get(queryParamsFilter.getName()));
						//predicate = chooseOrAnd(predicate, criteriaBuilder.gt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case ISNULL:
						predicate = chooseOrAnd(predicate, criteriaBuilder.isNull(root.get(queryParamsFilter.getName())), criteriaBuilder, type);
						break;
					case ISNOTNULL:
						predicate = chooseOrAnd(predicate, criteriaBuilder.isNotNull(root.get(queryParamsFilter.getName())), criteriaBuilder, type);
						break;
					case BETWEEN:
						//predicate = criteriaBuilder.between(root.get(queryParamsFilter.getName(),1,2);
						//predicate = chooseOrAnd(predicate, criteriaBuilder.gt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					default:
				}
			}
		}
		return predicate;
	}

	public Predicate chooseOrAnd(Predicate basicPredicate, Predicate newPredicate, CriteriaBuilder criteriaBuilder, String type){
		if ("and".equalsIgnoreCase(type)) {
			return criteriaBuilder.and(basicPredicate, newPredicate);
		} else if ("or".equalsIgnoreCase(type)) {
			return criteriaBuilder.or(basicPredicate, newPredicate);
		} else {
			System.out.println("type error!!!");
			return basicPredicate;
		}
	}

}
