package com.calm.dao.helper.field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.type.TypeMirror;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class ComparableFieldProcessor extends AbstractFieldProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComparableFieldProcessor.class);
    private static final Set<String> SUPPORT_TYPES = new HashSet<>();

    static {
        try {
            Enumeration<URL> resources = ComparableFieldProcessor.class.getClassLoader().getResources("META-INF/processor/" + Comparable.class.getName());
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    Stream<String> lines = bufferedReader.lines();
                    lines.forEach(SUPPORT_TYPES::add);
                }
            }
        } catch (IOException e) {
            LOGGER.error("load Comparable", e);
        }
    }

    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return SUPPORT_TYPES.contains(typeMirror.toString());
    }

}
