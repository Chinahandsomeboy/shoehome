package com.springboot.shoehome.utils;

import com.springboot.shoehome.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author acer
 * @date 2018/7/30
 */
public class Query<T> implements Specification<T> {

	enum Type{Or,And}

	private List<QueryParamsFilter> andFilters = new ArrayList<>();
	private List<QueryParamsFilter> orFilters = new ArrayList<>();
	private List<QueryJoinFilter> joinFilters = new ArrayList<>();
	private Map<String, Join> joinMap = new HashMap<>();

	public void and(QueryParamsFilter... queryParamsFilters) {
		for (QueryParamsFilter queryParamsFilter : queryParamsFilters) {
			andFilters.add(queryParamsFilter);
		}
	}

	public void or(QueryParamsFilter... queryParamsFilters) {
		orFilters = Arrays.asList(queryParamsFilters);
	}

	public Query leftJoin(String tableName) {
		joinFilters.add(QueryJoinFilter.leftJoin(tableName));
		return this;
	}

	public Query rightJoin(String tableName) {
		joinFilters.add(QueryJoinFilter.rightJoin(tableName));
		return this;
	}

	public Query innerJoin(String tableName) {
		joinFilters.add(QueryJoinFilter.innerJoin(tableName));
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

		// and 和or 条件没有同时用的情况, 进行formate sql
		Predicate predicate = null;
		addJoin(joinFilters, root);
		predicate = criteriaBuilder.equal(joinMap.get("customer").get("note"),"2222");
		if(andFilters.size() != 0 && orFilters.size() == 0){
			return parseFilters(andFilters, criteriaBuilder, root, predicate, Type.And);
		}else if(andFilters.size() == 0 && orFilters.size() != 0){
			return parseFilters(orFilters, criteriaBuilder, root, predicate, Type.Or);
		}else {
			System.out.println("none predicate");
		}
		return null;
	}

	public Predicate parseFilters(List<QueryParamsFilter> QueryParams, CriteriaBuilder criteriaBuilder, Root<T> root, Predicate predicate, Enum type) {
		if (QueryParams != null){
			for (QueryParamsFilter queryParamsFilter : QueryParams) {

				switch (queryParamsFilter.getType()) {
					case EQ:
						predicate = chooseOrAnd(predicate, criteriaBuilder.equal(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case NE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.notEqual(root.get(queryParamsFilter.getName()), queryParamsFilter.getValue()), criteriaBuilder, type);
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

	public Predicate chooseOrAnd(Predicate basicPredicate, Predicate newPredicate, CriteriaBuilder criteriaBuilder, Enum  type){
		if(basicPredicate == null){
			return newPredicate;
		}else{
			if (type == Type.And) {
				return criteriaBuilder.and(basicPredicate, newPredicate);
			} else if (type == Type.Or) {
				return criteriaBuilder.or(basicPredicate, newPredicate);
			}else {
				//抛异常 sout代替
				System.out.println("chooseOrAnd function error");
				return null;
			}
		}
	}

	public void addJoin(List<QueryJoinFilter> queryJoinFilters, Root<T> root){
		for (QueryJoinFilter queryJoinFilter : queryJoinFilters) {
			joinMap.put(queryJoinFilter.getTable(), root.join(queryJoinFilter.getTable(), queryJoinFilter.getJoinType()));
		}
	}

	public String[] analyzeParamsName(String paramsName){
		//解析join表的name属性
		String[] s = paramsName.split(".");
		return s;
	}

}
