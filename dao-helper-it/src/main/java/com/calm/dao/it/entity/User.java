package com.calm.dao.it.entity;

import com.calm.dao.helper.PersistenceFramework;
import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.helper.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Helper(framework = PersistenceFramework.JPA, packageName = "com.calm.dao.it.query")
public class User implements BaseEntity<Integer> {
    @Id
    @TableGenerator(name = "user", table = "sequence", pkColumnName = "key", valueColumnName = "value")
    @GeneratedValue(generator = "user")
    private Integer id;
    private int age;
    private String name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
