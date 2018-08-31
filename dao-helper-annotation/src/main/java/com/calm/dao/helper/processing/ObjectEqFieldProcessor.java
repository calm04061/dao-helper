package com.calm.dao.helper.processing;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class ObjectEqFieldProcessor implements FieldProcessor {
    @Override
    public boolean isSupport() {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name=var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder("and"+name+"Eq");

        TypeName typeName = TypeName.get(var.asType());
        ParameterSpec.Builder builder1 = ParameterSpec.builder(typeName, name);
        builder.addParameter(builder1.build());
        builder.addCode("return this;");
        return builder;
    }
}
