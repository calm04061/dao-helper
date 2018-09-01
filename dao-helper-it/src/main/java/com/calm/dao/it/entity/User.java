package com.calm.dao.it.entity;

import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.helper.entity.BaseEntity;
import com.calm.dao.it.AbstractHelperQuery;

@Helper(queryParent = AbstractHelperQuery.class, packageName = "com.calm.dao.it.query")
public class User implements BaseEntity<String> {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public Object getDisplayValue() {
        return null;
    }

    @Override
    public String getObjectName() {
        return null;
    }
}
