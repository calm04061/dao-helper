package com.calm.dao.helper.jpa.processor;

import com.calm.dao.helper.jpa.Operation2PredicateFinder;
import com.calm.dao.helper.method.MethodProcessor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;

import javax.lang.model.element.Name;

public abstract class AbstractConstuctorProcesson  implements MethodProcessor {
    protected void commonParameter(Name entityName, MethodSpec.Builder builder) {
        ClassName className = ClassName.bestGuess(entityName.toString());
        ClassName className1 = ClassName.get(Class.class);
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(className1, className);
        builder.addParameter(parameterizedTypeName, "entityType");
        builder.addParameter(Operation2PredicateFinder.class, "operation2PredicateFinder");
    }
}
