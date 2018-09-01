package com.calm.dao.helper.condition.filter;

import com.calm.dao.helper.condition.Condition;

/**
 * 筛选条件
 * @author dingqihui
 *
 */
public class FilterCondition extends Condition {
	private FilterType type;

	public FilterCondition(FilterType type, String property) {
		super(property);
		this.type = type;
	}

	public FilterType getType() {
		return type;
	}

	public void setType(FilterType type) {
		this.type = type;
	}
}
