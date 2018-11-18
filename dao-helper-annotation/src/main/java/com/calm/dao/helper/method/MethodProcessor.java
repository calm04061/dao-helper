package com.calm.dao.helper.method;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

public interface MethodProcessor {
    boolean isSupport(ClassName superClassName);

    MethodSpec.Builder buildMethod(ClassName superClassName, ClassName queryClassName);
}
