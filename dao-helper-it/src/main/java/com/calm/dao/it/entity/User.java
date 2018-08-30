package com.calm.dao.it.entity;

import javax.persistence.Entity;
import java.io.Serializable;
@Entity
public class User implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
