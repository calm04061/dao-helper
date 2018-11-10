package com.calm.dao.helper.jpa;

import javax.persistence.criteria.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ding on 2016/9/11.
 */
public class PathMap {
    private String property;
    private Path<?> path;
    private Map<String,PathMap> children = new HashMap<>();

    public PathMap(String property, Path<?> path) {
        this.property = property;
        this.path = path;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Path<?> getPath() {
        return path;
    }

    public void setPath(Path<?> path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathMap pathMap = (PathMap) o;

        return property != null ? property.equals(pathMap.property) : pathMap.property == null;

    }

    public Map<String, PathMap> getChildren() {
        return children;
    }

    public void setChildren(Map<String, PathMap> children) {
        this.children = children;
    }

    @Override
    public int hashCode() {
        return property != null ? property.hashCode() : 0;
    }
}
