package com.calm.dao.helper.jpa.factory;

import com.calm.dao.helper.condition.filter.Operation;
import com.calm.dao.helper.jpa.Operation2Predicate;
import com.calm.dao.helper.jpa.Operation2PredicateFinder;

import java.util.EnumMap;
import java.util.Map;

public class DefaultOperation2PredicateFinder implements Operation2PredicateFinder {
    private Map<Operation, Operation2Predicate> operation2PredicateMap = new EnumMap<Operation, Operation2Predicate>(Operation.class);
    public DefaultOperation2PredicateFinder(){
        operation2PredicateMap.put(Operation.EQ,new EqOperation2Predicate());
        operation2PredicateMap.put(Operation.LIKE,new LikeOperation2Predicate());
        operation2PredicateMap.put(Operation.GE,new GeOperation2Predicate());
        operation2PredicateMap.put(Operation.GT,new GtOperation2Predicate());
        operation2PredicateMap.put(Operation.LE,new LeOperation2Predicate());
        operation2PredicateMap.put(Operation.LT,new LtOperation2Predicate());
        operation2PredicateMap.put(Operation.NE,new NeOperation2Predicate());
    }
    @Override
    public Operation2Predicate find(Operation operation) {
        return operation2PredicateMap.get(operation);
    }
}
