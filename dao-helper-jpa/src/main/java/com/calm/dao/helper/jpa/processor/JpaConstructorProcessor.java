package com.calm.dao.helper.jpa.processor;

import com.calm.dao.helper.constructor.ConstructorProcessor;
import com.calm.dao.helper.jpa.Operation2PredicateFinder;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import javax.persistence.EntityManager;

@AutoService({ConstructorProcessor.class})
public class JpaConstructorProcessor implements ConstructorProcessor {
    @Override
    public boolean isSupport(ClassName superClassName) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(ClassName superClassName) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        builder.addParameter(EntityManager.class, "entityManager");
        builder.addParameter(Class.class, "entityType");
        builder.addParameter(Operation2PredicateFinder.class, "operation2PredicateFinder");
        builder.addCode("super($N, $N, $N);", "entityManager", "entityType", "operation2PredicateFinder");
        return builder;
    }
}
