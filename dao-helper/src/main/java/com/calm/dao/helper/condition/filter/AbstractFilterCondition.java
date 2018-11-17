package com.calm.dao.helper.condition.filter;

import com.calm.dao.helper.condition.AbstractPropertyCondition;
import com.calm.dao.helper.condition.FilterCondition;

/**
 * 筛选条件
 *
 * @author dingqihui
 */
public class AbstractFilterCondition extends AbstractPropertyCondition implements FilterCondition {
    private FilterType type;

    public AbstractFilterCondition(FilterType type, String property) {
        super(property);
        this.type = type;
    }

    public FilterType getType() {
        return type;
    }
}
