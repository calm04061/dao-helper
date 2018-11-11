package com.calm.dao.helper.field.or;

import com.calm.dao.helper.field.FieldProcessor;

import javax.lang.model.type.TypeMirror;

public abstract class StringFieldProcessor extends AbstractFieldProcessor implements FieldProcessor {
    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return typeMirror.toString().equals(String.class.getName());
    }

}
