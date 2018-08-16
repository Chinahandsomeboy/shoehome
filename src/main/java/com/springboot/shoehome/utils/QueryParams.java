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

	private List<QueryParamsFilter> andFilters = new ArrayList<>();
	private List<QueryParamsFilter> orFilters = new ArrayList<>();

	public void and(QueryParamsFilter... queryParamsFilters) {
		andFilters.add((QueryParamsFilter)Arrays.asList( queryParamsFilters));
	}

	public void or(QueryParamsFilter... queryParamsFilters) {
		orFilters.add((QueryParamsFilter)Arrays.asList( queryParamsFilters));
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
		Predicate p = criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "1"), criteriaBuilder.equal(root.get("code"), "1"));
		Predicate p1 = criteriaBuilder.or(criteriaBuilder.equal(root.get("note"), "1"));
		Predicate conjunction = criteriaBuilder.conjunction();
		Predicate disjunction = criteriaBuilder.disjunction();
		//Path<String> name = root.get("name");
		// Path<String> note = root.get("note");
		//Predicate p1 = criteriaBuilder.equal(name ,"1");
		//Predicate p2 = criteriaBuilder.equal(note ,"1");
		return criteriaBuilder.or(p, p1);
	}

	public List<Predicate> parseFilters(QueryParamsFilter[] queryParamsFilters, CriteriaBuilder criteriaBuilder, Root<T> root, String type) {
		List<Predicate> predicateList = new ArrayList<>();
		Predicate predicate;

		for (QueryParamsFilter queryParamsFilter : queryParamsFilters) {

			switch (queryParamsFilter.getType()) {
				case EQ:
					predicate = criteriaBuilder.equal(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue());
					break;
				case NE:
					predicate = criteriaBuilder.notEqual(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue());
					break;
				case GT:
					predicate = criteriaBuilder.gt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue());
					break;
				case LT:
					predicate = criteriaBuilder.lt(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue());
					break;
				case GE:
					predicate = criteriaBuilder.ge(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue());
					break;
				case LE:
					predicate = criteriaBuilder.le(root.get(queryParamsFilter.getName()), (int) queryParamsFilter.getValue());
					break;
				case LIKE:
					predicate = criteriaBuilder.like(root.get(queryParamsFilter.getName()),(String)queryParamsFilter.getValue());
					break;
				case IN:
					predicate = criteriaBuilder.in(root.get(queryParamsFilter.getName()));
					break;
				case ISNULL:
					predicate = criteriaBuilder.isNull(root.get(queryParamsFilter.getName()));
					break;
				case ISNOTNULL:
					predicate = criteriaBuilder.isNotNull(root.get(queryParamsFilter.getName()));
					break;
				case BETWEEN:
					//predicate = criteriaBuilder.between(root.get(queryParamsFilter.getName(),1,2);
					break;
				default:
			}
			predicateList.add(predicate);
		}

		return null;
	}
}
