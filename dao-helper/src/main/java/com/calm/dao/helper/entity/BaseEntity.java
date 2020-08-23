package com.calm.dao.helper.entity;

import java.io.Serializable;

/**
 * @author dingqihui
 */
//@Embeddable
public interface BaseEntity<I extends Serializable> extends Serializable {

    I getId();

    void setId(I id);

    String getDisplayName();

    default Object getDisplayValue() {
        return getId();
    }

    String getObjectName();
}
