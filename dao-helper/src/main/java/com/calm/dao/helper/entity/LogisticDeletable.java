package com.calm.dao.helper.entity;

public interface LogisticDeletable {
    String PROPERTY_NAME = "deleteClass";

    void setDeleteClass(Boolean deleteClass);

    Boolean getDeleteClass();

}
