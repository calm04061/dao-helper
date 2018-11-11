package com.calm.dao.helper.field;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

@AutoService({FieldProcessor.class})
public class InListFieldProcessor extends AbstractFieldProcessor {

    @Override
    public String option() {
        return "In";
    }

    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(VariableElement var) {
        String name = var.getSimpleName().toString();
        MethodSpec.Builder builder = MethodSpec.methodBuilder(name + option());
        boolean primitive = var.asType().getKind().isPrimitive();

        TypeName typeName = TypeName.get(var.asType());
        ParameterSpec.Builder start;
        if(primitive){
            ClassName list = ClassName.get("java.util", "List");
            start = ParameterSpec.builder(list, "values");
        }else{
            ClassName list = ClassName.get("java.util", "List");
            TypeName values = ParameterizedTypeName.get(list, typeName);
            start = ParameterSpec.builder(values, "values");
        }

        builder.addParameter(start.build());
        builder.addStatement("in(\"" + var + "\",  values )");
        builder.addStatement("return this");
        return builder;
    }

    public void buildMethodBody(MethodSpec.Builder builder, VariableElement var, String name) {
        throw new UnsupportedOperationException();
    }
}
