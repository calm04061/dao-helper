package com.calm.dao.helper.entity;

import java.util.Date;

public interface CreateTime {
    String PROPERTY_NAME = "createTime";
    Date getCreateTime();

    void setCreateTime(Date createTime);
}
