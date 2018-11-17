package com.calm.dao.helper.jpa.condition;

import com.calm.dao.helper.condition.filter.OperationItem;
import com.calm.dao.helper.jpa.PathMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public interface PropertyCondition2Predicate {

    Predicate toPredicate(String property, OperationItem<?> item2, Root<?> from, Map<String, PathMap> pathMap, CriteriaBuilder criteriaBuilder);
}
