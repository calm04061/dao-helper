package com.calm.dao.helper.jpa.factory;

import com.calm.dao.helper.jpa.Operation2Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * Created by ding on 2016/8/2.
 */

public class EqOperation2Predicate implements Operation2Predicate<Object> {
    @Override
    public Predicate full(CriteriaBuilder criteriaBuilder, From<?, ?> from, Path<Object> path, Object temp) {
        return criteriaBuilder.equal(path, temp);
    }

}
