package com.calm.dao.helper.jpa.processor;

import com.calm.dao.helper.method.MethodProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

@AutoService({MethodProcessor.class})
public class OrMethodProcessor implements MethodProcessor {
    @Override
    public boolean isSupport(ClassName superClassName) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(ClassName superClassName, ClassName queryClassName) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("or");

        builder.addStatement("return this");
        builder.returns(queryClassName);
        return builder;
    }
}
