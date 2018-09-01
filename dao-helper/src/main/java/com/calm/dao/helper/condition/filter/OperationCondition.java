package com.calm.dao.helper.condition.filter;

/**
 * ��ѯ����
 * @author dingqihui
 *
 */
public abstract class OperationCondition extends ValueFilterCondition<OperationItem<?>> {

	public OperationCondition(FilterType type,String property,OperationItem<?> item) {
		super(type,property,item);
	}
	
}
