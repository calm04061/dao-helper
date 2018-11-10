package com.calm.dao.helper.processing;

import com.calm.dao.helper.PersistenceFramework;

public class HelperInfo {
    private String packageName;
    private PersistenceFramework persistenceFramework;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public PersistenceFramework getPersistenceFramework() {
        return persistenceFramework;
    }

    public void setPersistenceFramework(PersistenceFramework persistenceFramework) {
        this.persistenceFramework = persistenceFramework;
    }
}
