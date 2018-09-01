package com.calm.dao.helper.condition.filter;

/**
 * and条件
 * @author dingqihui
 *
 */
public class InCondition extends ValueFilterCondition<Object[]> {
	
	public InCondition(FilterType type,String property,Object[] item) {
		super(type,property,item);
	}
}
