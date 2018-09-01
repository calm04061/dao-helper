package com.calm.dao.helper.condition.filter;

/**
 * ��ѯ��������
 * 
 * @author dingqihui
 *
 */
public class OperationItem<V> {
	private Operation operation;
	private V value;

	public OperationItem(Operation operation, V value) {
		this.operation = operation;
		this.value = value;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
