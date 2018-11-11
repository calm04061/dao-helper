package com.calm.dao.it.entity;

import com.calm.dao.helper.jpa.JpaDao;
import com.calm.dao.it.query.UserQuery;

public class UserDao extends JpaDao<String, User, UserQuery> {

    @Override
    public UserQuery query() {
        return new UserQuery(getEntityManager(), getEntityType(), getOperation2PredicateFinder());
    }
}