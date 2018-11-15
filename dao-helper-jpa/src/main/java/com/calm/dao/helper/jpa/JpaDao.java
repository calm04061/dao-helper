package com.calm.dao.helper.jpa;

import com.calm.dao.helper.IBaseDao;
import com.calm.dao.helper.Mapper;
import com.calm.dao.helper.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

public abstract class JpaDao<I extends Serializable, E extends BaseEntity<I>, Q extends JpaAbstractQuery<I, E>> implements IBaseDao<I, E, Q> {
    private EntityManager entityManager;
    private Operation2PredicateFinder operation2PredicateFinder;
    private Class<E> entityType;

    public JpaDao() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        entityType = (Class<E>) type.getActualTypeArguments()[1];
    }

    @Override
    public E loadById(I id) {
        return entityManager.find(entityType, id);
    }

    @Override
    @Transactional
    public E insert(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public E update(E entity) {
        return entityManager.merge(entity);
    }


    @Override
    @Transactional
    public void delete(List<E> entities) {
        for (E e : entities) {
            entityManager.remove(e);
        }
    }


    @Override
    public List<E> listByQuery(String jql, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadByQuery(String jql, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> listByNativeQuery(String sql, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadByNativeQuery(String sql, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E loadNativeQuery(String sql, Mapper<E> mapper, Object... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> listNativeQuery(String sql, Mapper<E> mapper, Object... args) {
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

    @Override
    @Transactional
    public void deleteById(I id) {
        E e = loadById(id);
        delete(Collections.singletonList(e));
    }

    @Override
    @Transactional
    public void saveAll(List<E> entity) {
        for (E e : entity) {
            I id = e.getId();
            if (id == null) {
                insert(e);
            } else {
                update(e);
            }
        }
    }

    @Override
    public Class<E> getEntityType() {
        return entityType;
    }
}
