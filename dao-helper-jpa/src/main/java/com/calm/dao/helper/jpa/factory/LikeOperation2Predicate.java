package com.calm.dao.helper.jpa.factory;

import com.calm.dao.helper.jpa.Operation2Predicate;

import javax.persistence.criteria.*;

/**
 * Created by ding on 2016/8/2.
 */
public class LikeOperation2Predicate implements Operation2Predicate<String> {
    @Override
    public Predicate full(CriteriaBuilder criteriaBuilder,From<?, ?> from,   Path<String> path, String temp) {
        return criteriaBuilder.like( path, temp);
    }
}
