package com.calm.dao.it.entity;

import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.it.AbstractHelperDao;

import java.io.Serializable;
@Helper(parent = AbstractHelperDao.class,packageName = "com.calm.dao.it.dao")
public class User implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
