package com.calm.dao.it;

import com.calm.dao.helper.Query;
import com.calm.dao.helper.Subquery;
import com.calm.dao.helper.condition.Condition;
import com.calm.dao.helper.condition.filter.FilterCondition;
import com.calm.dao.helper.condition.filter.MatchType;
import com.calm.dao.helper.entity.BaseEntity;
import com.calm.dao.helper.entity.Paging;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractHelperQuery<I extends Serializable, E extends BaseEntity<I>> implements Query<I, E> {
    @Override
    public Paging<E> paging(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<E> list() {
        return null;
    }

    @Override
    public E load() {
        return null;
    }

    @Override
    public Query<I, E> desc(String property) {
        return null;
    }

    @Override
    public Query<I, E> asc(String property) {
        return null;
    }

    @Override
    public Query<I, E> eq(String property, Object value) {
        return null;
    }

    @Override
    public Query<I, E> between(String property, Comparable start, Comparable end) {
        return null;
    }

    @Override
    public Query<I, E> in(String property, Object[] value) {
        return null;
    }

    @Override
    public Query<I, E> in(String property, List<?> value) {
        return null;
    }

    @Override
    public Query<I, E> ge(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> gt(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> lt(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> le(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> isNull(String property) {
        return null;
    }

    @Override
    public Query<I, E> notNull(String property) {
        return null;
    }

    @Override
    public Query<I, E> orBetween(String property, Comparable start, Comparable end) {
        return null;
    }

    @Override
    public Query<I, E> like(String property, String value) {
        return null;
    }

    @Override
    public Query<I, E> like(String property, String value, MatchType matchType) {
        return null;
    }

    @Override
    public Query<I, E> orEq(String property, Object value) {
        return null;
    }

    @Override
    public Query<I, E> orGe(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> orGt(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> orLt(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> orLe(String property, Comparable<?> value) {
        return null;
    }

    @Override
    public Query<I, E> orIsNull(String property) {
        return null;
    }

    @Override
    public Query<I, E> orNotNull(String property) {
        return null;
    }

    @Override
    public Query<I, E> orLike(String property, String value) {
        return null;
    }

    @Override
    public Query<I, E> orLike(String property, String value, MatchType matchType) {
        return null;
    }

    @Override
    public List<FilterCondition> getConditions() {
        return null;
    }

    @Override
    public List<Condition> getOrders() {
        return null;
    }

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public Subquery<I, E> orIn(String property, Object[] value) {
        return null;
    }

    @Override
    public <A extends Serializable, B extends BaseEntity<A>> Subquery<A, B> createSubquery(String property, Class<B> clazz) {
        return null;
    }

    @Override
    public Query<I, E> normal() {
        return null;
    }

    @Override
    public Query<I, E> ne(String property, Object value) {
        return null;
    }
}
