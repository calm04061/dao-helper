package com.calm.dao.helper.field.or;

import com.calm.dao.helper.field.FieldProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;

@AutoService({FieldProcessor.class})
public class ComparableGtFieldProcessor extends ComparableFieldProcessor {

    @Override
    public String option() {
        return "Gt";
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        builder.addStatement("orGt(\"" + var + "\"," + var + ")");
    }
}
