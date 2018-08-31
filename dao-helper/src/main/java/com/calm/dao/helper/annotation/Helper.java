package com.calm.dao.helper.annotation;

import com.calm.dao.helper.AbstractDao;

public @interface Helper {
    String packageName() default "—_-_-_-";

    Class<? extends AbstractDao> parent() default AbstractDao.class;
}
