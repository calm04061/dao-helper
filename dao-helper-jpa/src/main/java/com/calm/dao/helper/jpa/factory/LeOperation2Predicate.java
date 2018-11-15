package com.calm.dao.helper.jpa.factory;

import com.calm.dao.helper.jpa.Operation2Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * Created by ding on 2016/8/2.
 */

public class LeOperation2Predicate implements Operation2Predicate<Comparable> {
    @Override
    public Predicate full(CriteriaBuilder criteriaBuilder,From<?, ?> from,  Path<Comparable> path, Comparable temp) {
        return criteriaBuilder.lessThanOrEqualTo(path, temp);
    }
}
