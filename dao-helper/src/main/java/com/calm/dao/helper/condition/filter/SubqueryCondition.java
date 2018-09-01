package com.calm.dao.helper.condition.filter;

import com.calm.dao.helper.Subquery;

/**
 * 条件
 * 
 * @author dingqihui
 *
 */
public class SubqueryCondition extends FilterCondition {
	private Subquery<?, ?> query;

	public SubqueryCondition(String property, Subquery<?, ?> query) {
		super(FilterType.AND,property);
		this.query = query;
	}

	public Subquery<?, ?> getQuery() {
		return query;
	}

	public void setQuery(Subquery<?, ?> query) {
		this.query = query;
	}

}
