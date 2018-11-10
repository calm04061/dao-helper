package com.calm.dao.it.entity;

import com.calm.dao.helper.PersistenceFramework;
import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.helper.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Helper(framework = PersistenceFramework.JPA, packageName = "com.calm.dao.it.query")
public class User implements BaseEntity<String> {
    @Id
    private String id;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
