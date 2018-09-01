package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class StringLikeFieldProcessor extends StringFieldProcessor {

    @Override
    public String option() {
        return "Like";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        nullReturn(builder, name);
        builder.addStatement("andLike(\"" + var + "\",\"%\"+" + var + "+\"%\")");
    }
}