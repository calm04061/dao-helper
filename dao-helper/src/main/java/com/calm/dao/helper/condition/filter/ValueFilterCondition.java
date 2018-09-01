package com.calm.dao.helper.condition.filter;

public class ValueFilterCondition<T> extends FilterCondition {
	private T item;

	public ValueFilterCondition(FilterType type, String property, T item) {
		super(type, property);
		this.item = item;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
