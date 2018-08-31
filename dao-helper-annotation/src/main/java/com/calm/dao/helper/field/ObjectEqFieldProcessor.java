package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

@AutoService({FieldProcessor.class})
public class ObjectEqFieldProcessor extends AbstractFieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

    @Override
    public String option() {
        return "Eq";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        nullReturn(builder, name);
        builder.addStatement("add(\"" + var + "\"," + var + ")");
    }
}
