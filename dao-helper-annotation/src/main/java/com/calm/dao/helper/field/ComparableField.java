package com.calm.dao.helper.field;

import com.calm.dao.helper.field.or.ComparableFieldProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ComparableField {
    //    private static final Logger LOGGER = LoggerFactory.getLogger(ComparableFieldProcessor.class);
    public static final Set<String> SUPPORT_TYPES = new HashSet<>();

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
            System.err.println(e.getMessage());
        }
    }
}
