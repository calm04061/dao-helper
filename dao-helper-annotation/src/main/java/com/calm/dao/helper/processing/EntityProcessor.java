package com.calm.dao.helper.processing;

import com.calm.dao.helper.PersistenceFramework;
import com.calm.dao.helper.annotation.Helper;
import com.calm.dao.helper.method.MethodProcessor;
import com.calm.dao.helper.field.FieldProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AutoService({Processor.class})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EntityProcessor extends AbstractProcessor {
    private Filer filter;
    private Elements elementUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filter = processingEnvironment.getFiler();
        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
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
            for (Element element : elementsAnnotatedWith) {
                String elementPackage = elementUtils.getPackageOf(element).getQualifiedName().toString();
                TypeElement typeElement = (TypeElement) element;
                Name entityName = typeElement.getQualifiedName();
                String queryClassName = typeElement.getSimpleName().toString() + "Query";
                TypeSpec.Builder queryBuilder = TypeSpec.classBuilder(queryClassName);
                queryBuilder.addModifiers(Modifier.PUBLIC);
                ClassName idClassName = ClassName.bestGuess(findIdType(typeElement));
                ClassName entityClassName = ClassName.bestGuess(typeElement.getQualifiedName().toString());
                HelperInfo helperInfo = load(typeElement, elementPackage);
                ClassName superClassName = ClassName.bestGuess(helperInfo.getPersistenceFramework().getQueryParent());
                String packageName = helperInfo.getPackageName();
                ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(superClassName, idClassName, entityClassName);
                queryBuilder.superclass(parameterizedTypeName);
                ClassName dao = ClassName.get(packageName, queryClassName);

                methodProcessor(queryBuilder, superClassName, dao, entityName);
                fieldProcess(typeElement, queryBuilder, dao);
                JavaFile.Builder builder = JavaFile.builder(packageName, queryBuilder.build());
                builder.build().writeTo(filter);
            }

        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    private void fieldProcess(TypeElement typeElement, TypeSpec.Builder test, ClassName dao) {
        List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
        ServiceLoader<FieldProcessor> load = ServiceLoader.load(FieldProcessor.class, FieldProcessor.class.getClassLoader());
        Iterator<FieldProcessor> iterator = load.iterator();
        List<Element> fields = enclosedElements
                .stream()
                .filter(e -> e.getKind() == ElementKind.FIELD)
                .filter(e -> {
                    Set<Modifier> modifiers = e.getModifiers();
                    if (modifiers.contains(Modifier.STATIC)) {
                        return false;
                    }
                    return !modifiers.contains(Modifier.FINAL);
                })
                .collect(Collectors.toList());
        iterator.forEachRemaining(next -> {
            for (Element field : fields) {
                VariableElement var = (VariableElement) field;
                if (next.isSupport(var.asType())) {
                    MethodSpec.Builder builder = next.buildMethod(var);
                    builder.addModifiers(Modifier.PUBLIC);
                    builder.returns(dao);
                    test.addMethod(builder.build());
                }
            }
        });
    }

    private void methodProcessor(TypeSpec.Builder typeBuilder, ClassName superClassName, ClassName queryClassName, Name entityName) {
        ServiceLoader<MethodProcessor> load = ServiceLoader.load(MethodProcessor.class, MethodProcessor.class.getClassLoader());
        for (MethodProcessor next : load) {
            if (next.isSupport(superClassName)) {
                MethodSpec.Builder builder = next.buildMethod(entityName, superClassName, queryClassName);
                builder.addModifiers(Modifier.PUBLIC);
                typeBuilder.addMethod(builder.build());
            }
        }
    }

    private String findIdType(TypeElement typeElement) {
        List<? extends TypeMirror> typeParameters = typeElement.getInterfaces();
        for (TypeMirror typeMirror : typeParameters) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            String type = declaredType.asElement().toString();
            if (type.equals("com.calm.dao.helper.entity.BaseEntity") || type.equals("com.calm.dao.helper.entity.AbstractTreeEntity")) {
                DeclaredType argsType = (DeclaredType) typeMirror;
                List<? extends TypeMirror> typeArguments = argsType.getTypeArguments();
                if (typeArguments == null || typeArguments.isEmpty()) {
                    return null;
                }
                return typeArguments.get(0).toString();
            }
        }
        return null;
    }

    private HelperInfo load(TypeElement typeElement, String elementPackage) {
        List<? extends AnnotationMirror> annotation = typeElement.getAnnotationMirrors();

        HelperInfo helperInfo = new HelperInfo();
        helperInfo.setPackageName(elementPackage);
        for (AnnotationMirror annotationMirror : annotation) {
            DeclaredType annotationType = annotationMirror.getAnnotationType();
            String s = annotationType.toString();
            if (s.equals(Helper.class.getName())) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                elementValues.forEach((key, value) -> {
                    String attr = key.getSimpleName().toString();
                    if (attr.equals("framework")) {
                        helperInfo.setPersistenceFramework(PersistenceFramework.valueOf(value.getValue().toString()));
                    } else if (attr.equals("packageName")) {
                        helperInfo.setPackageName(value.getValue().toString());
                    }
                });
            }
        }
        return helperInfo;
    }
}
