package com.calm.dao.helper.condition;

/**
 * 条件
 * 
 * @author dingqihui
 *
 */
public abstract class Condition {
	private String property;

	public Condition() {

	}

	public Condition(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
