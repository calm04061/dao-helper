package com.calm.dao.helper.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * Created by ding on 2016/8/2.
 */
public interface Operation2Predicate<T> {
    Predicate full(CriteriaBuilder criteriaBuilder, From<?, ?> from, Path<T> path, T temp);
}
