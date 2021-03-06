package com.calm.dao.helper.field.and;

import com.calm.dao.helper.field.FieldProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.VariableElement;


public abstract class AbstractFieldProcessor implements FieldProcessor {
    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name = var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder(name + option());

        TypeName typeName = TypeName.get(var.asType());
        ParameterSpec.Builder builder1 = ParameterSpec.builder(typeName, name);
        builder.addParameter(builder1.build());
        buildMethodBody(builder, var, name);
        builder.addStatement("return this");
        return builder;
    }

    public abstract String option();

    protected abstract void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name);

    public String upperFirst(String name) {
        StringBuilder temp = new StringBuilder(name.substring(0, 1).toUpperCase());
        if (name.length() > 1) {
            temp.append(name.substring(1));
        }
        return temp.toString();
    }

}
