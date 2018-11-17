package com.calm.dao.helper.annotation;

import com.calm.dao.helper.PersistenceFramework;

public @interface Helper {
    String packageName() default "—_-_-_-";

    PersistenceFramework framework();
}
