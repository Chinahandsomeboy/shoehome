package com.springboot.shoehome.utils;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.domain.ItemSmallType;
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
	//private Map<String, Join> joinMap = new HashMap<>();

	public void and(QueryParamsFilter... queryParamsFilters) {
		andFilters.addAll(Arrays.asList(queryParamsFilters));
	}

	public void or(QueryParamsFilter... queryParamsFilters) {
		orFilters.addAll(Arrays.asList(queryParamsFilters));
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
		//root.getAlias()
		//root.alias("_SO_");
		//Join<root,customer>join  = root.join("customer",JoinType.LEFT).alias("_C_");
		//predicate = criteriaBuilder.equal(root.get("_C_").get("name"),"11");
		Predicate predicate = null;
		addJoin(joinFilters, root);
		Order order = criteriaBuilder.asc(root.get("customer").get("balance"));
		Order order1 = criteriaBuilder.asc(root.get("finalPrice"));
		criteriaQuery.orderBy(order1, order);//排序先后决定权值
		if(andFilters.size() != 0 && orFilters.size() == 0){
			return parseFilters(andFilters, criteriaBuilder, root, predicate, Type.And);
		}else if(andFilters.size() == 0 && orFilters.size() != 0){
			return parseFilters(orFilters, criteriaBuilder, root, predicate, Type.Or);
		}else if (andFilters.size() != 0 && orFilters.size() != 0){
			return parseFilters(andFilters, criteriaBuilder, root, parseFilters(orFilters, criteriaBuilder, root, predicate, Type.Or), Type.And);
		}else {
			System.out.println("none predicate");
		}
		return null;
	}

	public  Predicate parseFilters(List<QueryParamsFilter> queryParams, CriteriaBuilder criteriaBuilder, Root<T> root, Predicate predicate, Enum type) {
		if (queryParams != null){
			for (QueryParamsFilter queryParamsFilter : queryParams) {
				switch (queryParamsFilter.getType()) {
					case EQ:
						predicate = chooseOrAnd(predicate, criteriaBuilder.equal(analyzeParamsName(queryParamsFilter.getName(), root), queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case NE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.notEqual(analyzeParamsName(queryParamsFilter.getName(), root), queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case GT:
						predicate = chooseOrAnd(predicate, criteriaBuilder.gt(analyzeParamsName(queryParamsFilter.getName(), root), (Number) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LT:
						predicate = chooseOrAnd(predicate, criteriaBuilder.lt(analyzeParamsName(queryParamsFilter.getName(), root), (Number) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case GE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.ge(analyzeParamsName(queryParamsFilter.getName(), root), (Number) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.le(analyzeParamsName(queryParamsFilter.getName(), root), (Number) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case LIKE:
						predicate = chooseOrAnd(predicate, criteriaBuilder.like(analyzeParamsName(queryParamsFilter.getName(), root), (String) queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case IN:
						predicate = chooseOrAnd(predicate, root.get(queryParamsFilter.getName()).in(queryParamsFilter.getValue()), criteriaBuilder, type);
						break;
					case ISNULL:
						predicate = chooseOrAnd(predicate, criteriaBuilder.isNull(analyzeParamsName(queryParamsFilter.getName(), root)), criteriaBuilder, type);
						break;
					case ISNOTNULL:
						predicate = chooseOrAnd(predicate, criteriaBuilder.isNotNull(analyzeParamsName(queryParamsFilter.getName(), root)), criteriaBuilder, type);
						break;
					case BETWEEN:
						predicate = chooseOrAnd(predicate, criteriaBuilder.between(analyzeParamsName(queryParamsFilter.getName(), root), (Comparable)(((List)queryParamsFilter.getValue()).get(0)), (Comparable)(((List)queryParamsFilter.getValue()).get(1))), criteriaBuilder, type);
						break;
					default:
				}
			}
		}
		return predicate;
	}

	public Predicate chooseOrAnd(Predicate basicPredicate, Predicate newPredicate, CriteriaBuilder criteriaBuilder, Enum type){
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
			//joinMap.put(queryJoinFilter.getTable(), root.join(queryJoinFilter.getTable(), queryJoinFilter.getJoinType()));
			root.join(queryJoinFilter.getTable(), queryJoinFilter.getJoinType());
		}
	}

	public Path analyzeParamsName(String paramsName, Root<T> root){
		//解析join表的name属性 长度是1表示根节点的条件, 长度是2 表示join表的条件 表名加上字段名
		String[] params = paramsName.split("\\.");
		if(params.length == 1){
			return root.get(params[0]);
		}else if(params.length == 2){
			return root.get(params[0]).get(params[1]);
			//return joinMap.get(params[0]).get(params[1]);
		}
		 return null;
	}

}
