package com.calm.dao.helper.condition.filter;

import com.calm.dao.helper.Query;

/**
 * @author dingqihui
 */
public class ListCondition extends ValueFilterCondition<Query<?,?>> {

	public ListCondition(FilterType type,Query<?,?> item) {
		super(type,null,item);
	}

	
}
