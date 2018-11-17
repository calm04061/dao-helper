package com.calm.dao.helper.condition;

/**
 * 条件
 *
 * @author dingqihui
 */
public abstract class AbstractPropertyCondition implements PropertyCondition {
    private String property;

    public AbstractPropertyCondition() {

    }

    public AbstractPropertyCondition(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
