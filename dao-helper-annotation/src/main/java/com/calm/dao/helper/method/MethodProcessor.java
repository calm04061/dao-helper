package com.calm.dao.helper.method;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.Name;

public interface MethodProcessor {
    boolean isSupport(ClassName superClassName);

    MethodSpec.Builder buildMethod(Name entityName, ClassName superClassName, ClassName queryClassName);
}
