package com.calm.dao.helper.processing;

import com.calm.dao.helper.AbstractDao;
import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.helper.field.FieldProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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
        annotations.add(Helper.class.getCanonicalName());
        return annotations;
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Helper.class);
            for(Element element:elementsAnnotatedWith){
                String elementPackage = mElementUtils.getPackageOf(element).getQualifiedName().toString();
                TypeElement typeElement= (TypeElement) element;
                List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
                List<Element> fields=enclosedElements
                        .stream()
                        .filter(e-> e.getKind()== ElementKind.FIELD)
                        .collect(Collectors.toList());
                TypeSpec.Builder test = TypeSpec.classBuilder(typeElement.getSimpleName().toString()+"Dao");
                List<? extends AnnotationMirror> annotation = typeElement.getAnnotationMirrors();
                AtomicReference<String> packageName = new AtomicReference<>();;
                packageName.set(elementPackage);
                AtomicReference<ClassName> parent = new AtomicReference<>();;
                parent.set(ClassName.bestGuess(AbstractDao.class.getName()));
                for(AnnotationMirror annotationMirror:annotation){
                    DeclaredType annotationType = annotationMirror.getAnnotationType();
                    String s = annotationType.toString();
                    if(s.equals(Helper.class.getName())){
                        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                        elementValues.forEach((key,value)->{
                            String attr=key.getSimpleName().toString();
                            if(attr.equals("parent")){
                                parent.set(ClassName.bestGuess(value.getValue().toString()));
                            }else if(attr.equals("packageName")){
                                packageName.set(value.getValue().toString());
                            }
                        });

                    }
                }
                test.superclass(parent.get());
                ClassName dao = ClassName.get(packageName.get(), typeElement.getSimpleName().toString()+"Dao");

                ServiceLoader<FieldProcessor> load = ServiceLoader.load(FieldProcessor.class,FieldProcessor.class.getClassLoader());
                Iterator<FieldProcessor> iterator = load.iterator();
                for(Element field:fields){
                    VariableElement var= (VariableElement) field;
                    while(iterator.hasNext()){
                        FieldProcessor next = iterator.next();
                        if(next.isSupport(var.asType())){
                            MethodSpec.Builder builder = next.buildMethod(var);
                            builder.addModifiers(Modifier.PUBLIC);
                            builder.returns(dao);
                            test.addMethod(builder.build());
                        }
                    }

                }

                JavaFile.Builder builder = JavaFile.builder(packageName.get(), test.build());
                builder.build().writeTo(mFiler);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
