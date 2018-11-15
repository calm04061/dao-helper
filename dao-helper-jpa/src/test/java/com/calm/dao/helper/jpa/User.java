package com.calm.dao.helper.jpa;

import com.calm.dao.helper.entity.BaseEntity;

public class User implements BaseEntity<Integer> {
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
