package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class StringEndWithFieldProcessor extends StringFieldProcessor {

    @Override
    public String option() {
        return "EndWith";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        nullReturn(builder, name);
        builder.addStatement("andLike(\"" + var + "\",\"%\"+" + var + ")");
    }
}
