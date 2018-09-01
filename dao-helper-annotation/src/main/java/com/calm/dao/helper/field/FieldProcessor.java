package com.calm.dao.helper.field;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

public interface FieldProcessor {
    boolean isSupport(TypeMirror typeMirror);

    MethodSpec.Builder buildMethod(VariableElement var);
}
