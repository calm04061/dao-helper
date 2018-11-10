package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class ComparableBetweenFieldProcessor extends ComparableFieldProcessor {

    @Override
    public String option() {
        return "Between";
    }

    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name = var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder("and" + upperFirst(name) + option());

        TypeName typeName = TypeName.get(var.asType());
        ParameterSpec.Builder start = ParameterSpec.builder(typeName, "start");
        ParameterSpec.Builder end = ParameterSpec.builder(typeName, "end");
        builder.addParameter(start.build());
        builder.addParameter(end.build());
        nullReturn(builder, var, "start");
        nullReturn(builder, var, "end");
        builder.addStatement("andBetween(\"" + var + "\",  start  ,  end )");
        builder.addStatement("return this");
        return builder;
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        throw new UnsupportedOperationException();
    }
}
