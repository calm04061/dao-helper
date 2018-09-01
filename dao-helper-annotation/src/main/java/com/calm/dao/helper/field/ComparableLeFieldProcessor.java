package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class ComparableLeFieldProcessor extends ComparableFieldProcessor {

    @Override
    public String option() {
        return "Le";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        nullReturn(builder, var, name);
        builder.addStatement("andLe(\"" + var + "\"," + var + ")");
    }
}
