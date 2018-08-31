package com.calm.dao.helper.processing;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.persistence.Entity;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AutoService({Processor.class})
public class EntityProcessor extends AbstractProcessor {
    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Entity.class.getCanonicalName());
        return annotations;
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Entity.class);
            for(Element element:elementsAnnotatedWith){
                TypeElement typeElement= (TypeElement) element;
                List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
                List<Element> fields=enclosedElements
                        .stream()
                        .filter(e-> e.getKind()== ElementKind.FIELD)
                        .collect(Collectors.toList());
                TypeSpec.Builder test = TypeSpec.classBuilder(typeElement.getSimpleName().toString()+"Dao");
                ClassName dao = ClassName.bestGuess(typeElement.getSimpleName().toString()+"Dao");

                ServiceLoader<FieldProcessor> load = ServiceLoader.load(FieldProcessor.class,FieldProcessor.class.getClassLoader());
                Iterator<FieldProcessor> iterator = load.iterator();
                for(Element field:fields){
                    VariableElement var= (VariableElement) field;
                    while(iterator.hasNext()){
                        FieldProcessor next = iterator.next();
                        if(next.isSupport()){
                            MethodSpec.Builder builder = next.buildMethod(var);
                            builder.addModifiers(Modifier.PUBLIC);
                            builder.returns(dao);
                            test.addMethod(builder.build());
                        }
                    }

                }

                JavaFile.Builder builder = JavaFile.builder(mElementUtils.getPackageOf(element).getQualifiedName().toString(), test.build());
                builder.build().writeTo(mFiler);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
