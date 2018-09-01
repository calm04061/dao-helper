package com.calm.dao.helper.condition;

import com.calm.dao.helper.condition.filter.*;

/**
 * 查询条件
 * 
 * @author dingqihui
 *
 */
public abstract class Conditions{
	private Conditions(){}
	public static SimpleCondition eq(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.EQ, value));
	}

	public static FilterCondition like(FilterType type, String property, String value) {
		return new SimpleCondition(type, property, new OperationItem<Object>(Operation.LIKE, value));
	}

	public static SimpleCondition gt(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.GT, value));
	}

	public static FilterCondition ge(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.GE, value));
	}

	public static FilterCondition lt(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.LT, value));
	}

	public static FilterCondition le(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.LE, value));
	}
	
	public static FilterCondition isNull(FilterType type, String property) {
		return new IsNullCondition(type, property);
	}
	public static FilterCondition isNotNull(FilterType type, String property) {
		return new IsNotNullCondition(type, property);
	}

	public static FilterCondition ne(FilterType type, String property, Object value) {
		return new SimpleCondition(type, property, new OperationItem<>(Operation.NE, value));
	}
}
