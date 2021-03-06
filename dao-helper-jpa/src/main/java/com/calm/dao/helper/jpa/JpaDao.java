package com.calm.dao.helper.jpa;

import com.calm.dao.helper.IBaseDao;
import com.calm.dao.helper.entity.BaseEntity;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

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
    public E insert(E entity) {
        return insert(Collections.singleton(entity)).get(0);
    }

    @Override
    public List<E> insert(Collection<E> entities) {
        for (E e : entities) {
            entityManager.persist(e);
        }
        return new ArrayList<>(entities);
    }

    @Override
    public List<E> update(Collection<E> entities) {
        for (E e : entities) {
            entityManager.merge(e);
        }
        return new ArrayList<>(entities);
    }

    @Override
    public E update(E entity) {
        return update(Collections.singleton(entity)).get(0);
    }

    @Override
    public void delete(E entity) {
        delete(Collections.singleton(entity));
    }

    @Override
    public void delete(Collection<E> entities) {
        for (E e : entities) {
            entityManager.remove(e);
        }
    }

    @Override
    public void deleteById(Collection<I> ids) {
        List<E> es = loadByIds(ids);
        delete(es);
    }

    @Override
    public void deleteById(I id) {
        E e = loadById(id);
        delete(Collections.singletonList(e));
    }

    @Override
    public List<E> saveAll(Collection<E> entities) {
        List<I> ids = entities.parallelStream().filter(e -> e.getId() != null).map(E::getId).collect(Collectors.toList());
        List<E> es = loadByIds(ids);
        Map<I, E> dbEntities = es.parallelStream().collect(Collectors.toMap(E::getId, e -> e));
        List<E> toInsert = new ArrayList<>();
        List<E> toUpdate = new ArrayList<>();
        for (E e : entities) {
            I id = e.getId();
            if (id == null) {
                toInsert.add(e);
            } else {
                E e1 = dbEntities.get(id);
                if (e1 == null) {
                    toInsert.add(e);
                } else {
                    toUpdate.add(e);
                }
            }
        }
        if (!toInsert.isEmpty()) {
            insert(toInsert);
        }
        if (!toUpdate.isEmpty()) {
            update(toUpdate);
        }
        return new ArrayList<>(entities);
    }

    @Override
    public E loadById(I id) {
        return entityManager.find(entityType, id);
    }

    @Override
    public List<E> loadByIds(Collection<I> id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(getEntityType());
        Root<E> from = query.from(getEntityType());
        Path<I> idPath = from.get("id");
        Predicate in = idPath.in(id);
        query.where(in);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<E> listByQuery(String jql, Object... args) {
        TypedQuery<E> query = getEntityManager().createQuery(jql, getEntityType());
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i + 1, args[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public E loadByQuery(String jql, Object... args) {
        TypedQuery<E> query = getEntityManager().createQuery(jql, getEntityType());
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i + 1, args[i]);
            }
        }
        return query.getSingleResult();
    }

    @Override
    public List<E> listByNativeQuery(String sql, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql, getEntityType());
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                nativeQuery.setParameter(i + 1, args[i]);
            }
        }
        return nativeQuery.getResultList();

    }

    @Override
    public E loadByNativeQuery(String sql, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql, getEntityType());
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                nativeQuery.setParameter(i + 1, args[i]);
            }
        }
        Object singleResult = nativeQuery.getSingleResult();
        return getEntityType().cast(singleResult);
    }

    @Override
    public E loadNativeQuery(String sql, String resultMapping, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql, resultMapping);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                nativeQuery.setParameter(i + 1, args[i]);
            }
        }
        Object singleResult = nativeQuery.getSingleResult();
        return getEntityType().cast(singleResult);
    }

    @Override
    public List<E> listNativeQuery(String sql, String resultMapping, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql, resultMapping);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                nativeQuery.setParameter(i + 1, args[i]);
            }
        }
        return nativeQuery.getResultList();
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
    public Class<E> getEntityType() {
        return entityType;
    }
}
