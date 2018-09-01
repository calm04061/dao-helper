package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class IsNotNullFieldProcessor extends NoArgsFieldProcessor {

    @Override
    public String option() {
        return "IsNotNull";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        builder.addStatement("andNotNull(\"" + var + "\")");
    }
}
