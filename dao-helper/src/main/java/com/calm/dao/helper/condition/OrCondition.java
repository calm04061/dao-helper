package com.calm.dao.helper.condition;

import com.calm.dao.helper.Query;
import com.calm.dao.helper.condition.filter.FilterType;
import com.calm.dao.helper.entity.BaseEntity;

import java.io.Serializable;

public class OrCondition<I extends Serializable, E extends BaseEntity<I>> implements FilterCondition {
    private Query<I, E> query;

    public OrCondition(Query<I, E> query) {
        this.query = query;
    }

    public Query<I, E> getQuery() {
        return query;
    }

    @Override
    public FilterType getType() {
        return FilterType.OR;
    }
}
