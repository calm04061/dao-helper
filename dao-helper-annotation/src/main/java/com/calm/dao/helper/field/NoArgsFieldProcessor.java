package com.calm.dao.helper.field;

import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

public abstract class NoArgsFieldProcessor extends AbstractFieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name = var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder("and" + upperFirst(name) + option());

        buildMethodBody(builder, var, name);
        builder.addStatement("return this");
        return builder;
    }
}
