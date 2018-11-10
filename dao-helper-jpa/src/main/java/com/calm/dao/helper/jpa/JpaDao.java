package com.calm.dao.helper.jpa;

import com.calm.dao.helper.IBaseDao;
import com.calm.dao.helper.Mapper;
import com.calm.dao.helper.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class JpaDao<I extends Serializable, E extends BaseEntity<I>, Q extends JpaAbstractQuery<I, E>> implements IBaseDao<I, E, Q> {
    private EntityManager entityManager;
    private Operation2PredicateFinder operation2PredicateFinder;
    @Override
    public E loadById(Class<E> clazz, I id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public E insert(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        return entityManager.merge(entity);
    }


    @Override
    public void delete(List<E> entities) {
        for (E e : entities) {
            entityManager.remove(e);
        }
    }


    @Override
    public List<E> listByQuery(String jql, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadByQuery(String jql, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> listByNativeQuery(String sql, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadByNativeQuery(String sql, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadNativeQuery(String sql, Mapper<E> mapper, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> listNativeQuery(String sql, Mapper<E> mapper, Class<E> clazz, Object... args) {
        throw new UnsupportedOperationException();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setOperation2PredicateFinder(Operation2PredicateFinder operation2PredicateFinder) {
        this.operation2PredicateFinder = operation2PredicateFinder;
    }

    public Operation2PredicateFinder getOperation2PredicateFinder() {
        return operation2PredicateFinder;
    }
}
