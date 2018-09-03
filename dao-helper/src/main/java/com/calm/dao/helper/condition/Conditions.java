package com.calm.dao.helper.condition;

import com.calm.dao.helper.condition.filter.*;

/**
 * 查询条件
 *
 * @author dingqihui
 */
public interface Conditions {
    static SimpleCondition eq(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.EQ, value));
    }

    static FilterCondition like(FilterType type, String property, String value) {
        return new SimpleCondition(type, property, new OperationItem<Object>(Operation.LIKE, value));
    }

    static SimpleCondition gt(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.GT, value));
    }

    static FilterCondition ge(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.GE, value));
    }

    static FilterCondition lt(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.LT, value));
    }

    static FilterCondition le(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.LE, value));
    }

    static FilterCondition isNull(FilterType type, String property) {
        return new IsNullCondition(type, property);
    }

    static FilterCondition isNotNull(FilterType type, String property) {
        return new IsNotNullCondition(type, property);
    }

    static FilterCondition ne(FilterType type, String property, Object value) {
        return new SimpleCondition(type, property, new OperationItem<>(Operation.NE, value));
    }

    static FilterCondition between(FilterType type, String property, Comparable start, Comparable end) {
        return new BetweenCondition(type, property, new Comparable[]{start, end});
    }
}
