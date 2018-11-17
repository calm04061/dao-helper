package com.calm.dao.helper.jpa.processor;

import com.calm.dao.helper.jpa.Operation2PredicateFinder;
import com.calm.dao.helper.method.MethodProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.persistence.EntityManager;

@AutoService({MethodProcessor.class})
public class JpaPrivateConstructorProcessor implements MethodProcessor {
    @Override
    public boolean isSupport(ClassName superClassName) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(ClassName superClassName, ClassName queryClassName) {

        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        builder.addParameter(queryClassName, "parent");
        builder.addParameter(Class.class, "entityType");
        builder.addParameter(Operation2PredicateFinder.class, "operation2PredicateFinder");
        builder.addCode("super($N, $N, $N);", "parent", "entityType", "operation2PredicateFinder");
        return builder;
    }
}
