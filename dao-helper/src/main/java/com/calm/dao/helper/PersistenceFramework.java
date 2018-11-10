package com.calm.dao.helper;

public enum PersistenceFramework {
    JPA("com.calm.dao.helper.jpa.JpaAbstractQuery", "com.calm.dao.helper.jpa.JpaDao");
    private String queryParent;
    private String daoParent;

    PersistenceFramework(String queryParent, String daoParent) {
        this.queryParent = queryParent;
        this.daoParent = daoParent;
    }

    public String getDaoParent() {
        return daoParent;
    }

    public String getQueryParent() {
        return queryParent;
    }
}
