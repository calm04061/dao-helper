package com.calm.dao.helper.field.order;

import com.calm.dao.helper.field.and.AbstractFieldProcessor;
import com.calm.dao.helper.field.FieldProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

@AutoService({FieldProcessor.class})
public class DescFieldProcessor extends AbstractFieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name = var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder(name + option());
        buildMethodBody(builder, var, name);
        builder.addStatement("return this");
        return builder;
    }

    @Override
    public String option() {
        return "Desc";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        builder.addStatement("desc(\"$N\")", var.toString());
    }
}
