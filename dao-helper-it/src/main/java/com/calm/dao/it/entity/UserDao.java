package com.calm.dao.it.entity;

import com.calm.dao.helper.jpa.JpaDao;
import com.calm.dao.helper.jpa.Operation2PredicateFinder;
import com.calm.dao.it.query.UserQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao extends JpaDao<Integer, User, UserQuery> {

    @Override
    public UserQuery query() {
        return new UserQuery(getEntityManager(), getEntityType(), getOperation2PredicateFinder());
    }

    @Resource
    @Override
    public void setOperation2PredicateFinder(Operation2PredicateFinder operation2PredicateFinder) {
        super.setOperation2PredicateFinder(operation2PredicateFinder);
    }

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}