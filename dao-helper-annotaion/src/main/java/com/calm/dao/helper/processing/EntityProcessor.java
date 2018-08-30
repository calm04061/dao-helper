package com.calm.dao.helper.processing;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
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
            JavaFileObject classFile = mFiler.createClassFile("");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
