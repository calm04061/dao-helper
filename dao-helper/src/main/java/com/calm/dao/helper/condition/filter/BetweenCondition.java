package com.calm.dao.helper.condition.filter;

/**
 * and条件
 * @author dingqihui
 *
 */
public class BetweenCondition extends ValueFilterCondition<Comparable[]> {

	public BetweenCondition(FilterType type, String property, Comparable[] item) {
		super(type,property,item);
	}
}
