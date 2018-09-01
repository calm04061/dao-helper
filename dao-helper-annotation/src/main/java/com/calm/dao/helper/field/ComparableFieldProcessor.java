package com.calm.dao.helper.field;

import javax.lang.model.type.TypeMirror;

public abstract class ComparableFieldProcessor extends AbstractFieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return true;
    }

}
