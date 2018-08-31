package com.calm.dao.helper.processing;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

public interface FieldProcessor {
    boolean isSupport();

    MethodSpec.Builder buildMethod(VariableElement var);
}
