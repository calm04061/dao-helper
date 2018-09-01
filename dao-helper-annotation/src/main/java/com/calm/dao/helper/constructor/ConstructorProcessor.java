package com.calm.dao.helper.constructor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

public interface ConstructorProcessor {
    boolean isSupport(ClassName superClassName);

    MethodSpec.Builder buildMethod(ClassName superClassName);
}
