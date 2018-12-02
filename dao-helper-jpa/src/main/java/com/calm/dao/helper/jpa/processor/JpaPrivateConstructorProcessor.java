package com.calm.dao.helper.jpa.processor;

import com.calm.dao.helper.jpa.Operation2PredicateFinder;
import com.calm.dao.helper.method.MethodProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;

import javax.lang.model.element.Name;
import javax.persistence.EntityManager;

@AutoService({MethodProcessor.class})
public class JpaPrivateConstructorProcessor extends AbstractConstuctorProcesson implements MethodProcessor {
    @Override
    public boolean isSupport(ClassName superClassName) {
        return true;
    }

    @Override
    public MethodSpec.Builder buildMethod(Name entityName, ClassName superClassName, ClassName queryClassName) {

        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        builder.addParameter(queryClassName, "parent");
        commonParameter(entityName, builder);
        builder.addCode("super($N, $N, $N);", "parent", "entityType", "operation2PredicateFinder");
        return builder;
    }


}
