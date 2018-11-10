package com.calm.dao.it.entity;

import com.calm.dao.helper.jpa.JpaDao;
import com.calm.dao.it.query.UserQuery;

public class UserDao extends JpaDao<String, User, UserQuery> {

    @Override
    public UserQuery query(Class<User> clazz) {
        return new UserQuery(getEntityManager(), clazz, getOperation2PredicateFinder());
    }
}