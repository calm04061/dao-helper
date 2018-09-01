package com.calm.dao.helper.annotation;

import com.calm.dao.helper.Subquery;

public @interface Helper {
    String packageName() default "—_-_-_-";

    Class<? extends Subquery> queryParent();
}
