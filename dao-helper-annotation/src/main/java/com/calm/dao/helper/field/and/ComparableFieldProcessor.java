package com.calm.dao.helper.field.and;

import com.calm.dao.helper.field.ComparableField;

import javax.lang.model.type.TypeMirror;

public abstract class ComparableFieldProcessor extends AbstractFieldProcessor {


    @Override
    public boolean isSupport(TypeMirror typeMirror) {
        return ComparableField.SUPPORT_TYPES.contains(typeMirror.toString());
    }

}
