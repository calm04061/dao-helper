package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

@AutoService({FieldProcessor.class})
public class IsNotNullFieldProcessor extends AbstractFieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

    @Override
    public String option() {
        return "IsNotNull";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        builder.addStatement("add(\"" + var + "\"," + var + ")");
    }
}
