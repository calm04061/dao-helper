package com.calm.dao.helper.field.and;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

public abstract class AbstractLikeFieldProcessor extends StringFieldProcessor {


    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        builder.addCode(String.format("if( %s == null || %s.trim().length()==0){\n", var, var));
        builder.addStatement("  return this");
        builder.addCode("}\n");

    }
}