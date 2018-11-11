package com.calm.dao.helper.jpa;

import com.calm.dao.helper.AbstractQuery;
import com.calm.dao.helper.Query;
import com.calm.dao.helper.Subquery;
import com.calm.dao.helper.condition.AscCondition;
import com.calm.dao.helper.condition.Condition;
import com.calm.dao.helper.condition.DescCondition;
import com.calm.dao.helper.condition.filter.*;
import com.calm.dao.helper.entity.BaseEntity;
import com.calm.dao.helper.entity.DefaultPaging;
import com.calm.dao.helper.entity.Paging;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

public class JpaAbstractQuery<I extends Serializable, E extends BaseEntity<I>> extends AbstractQuery<I, E> {
    private EntityManager entityManager;
    private Class<E> entityType;
    private Operation2PredicateFinder operation2PredicateFinder;


    public JpaAbstractQuery(EntityManager entityManager, Class<E> entityType, Operation2PredicateFinder operation2PredicateFinder) {
        this.entityManager = entityManager;
        this.entityType = entityType;
        this.operation2PredicateFinder = operation2PredicateFinder;
    }

    @Override
    public Paging<E> paging(int currentPage, int pageSize) {
        Map<String, PathMap> pathMap = new HashMap<>();

        // 组织查询条件
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> createQuery = criteriaBuilder.createQuery(entityType);

        Root<E> from = createQuery.from(entityType);

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

        // 记录总数
        Root<E> root = countQuery.from(entityType);
        Predicate[] predicate = getPredicate(this, root, pathMap, countQuery, criteriaBuilder);

        CriteriaQuery<Long> where = countQuery.where(predicate);

        List<Expression<?>> groups = getGroups(this, root, pathMap);
        where.groupBy(groups);
        where.select(criteriaBuilder.countDistinct(root));

        int count = entityManager.createQuery(where).getSingleResult().intValue();

        List<E> resultList;
        if (count > 0) {

            predicate = getPredicate(this, from, pathMap, createQuery, criteriaBuilder);
            createQuery.where(predicate);
            List<Order> order = getOrder(this, from, pathMap, criteriaBuilder);
            createQuery.orderBy(order.toArray(new Order[]{}));
            groups = getGroups(this, from, pathMap);
            createQuery.groupBy(groups);
            createQuery.select(from).distinct(true);
            TypedQuery<E> createQuery2 = entityManager.createQuery(createQuery);

            int temp = currentPage - 1;
            if (temp < 0) {
                temp = 0;
            }
            int start = temp * pageSize;

            createQuery2.setFirstResult(start).setMaxResults(pageSize);
            resultList = createQuery2.getResultList();
        } else {
            resultList = Collections.emptyList();
        }
        Paging<E> result = new DefaultPaging<>(currentPage, pageSize);
        result.setTotalCount(count);
        result.setData(resultList);
        return result;

    }

    @Override
    public List<E> list() {
        Map<String, PathMap> pathMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> createQuery = criteriaBuilder.createQuery(entityType);
        Root<E> from = createQuery.from(entityType);
        Predicate[] predicate = getPredicate(this, from, pathMap, createQuery, criteriaBuilder);
        createQuery.where(predicate);
        createQuery.select(from).distinct(true);
        List<Order> order = getOrder(this, from, pathMap, criteriaBuilder);
        createQuery.orderBy(order.toArray(new Order[]{}));
        return entityManager.createQuery(createQuery).getResultList();
    }

    @Override
    public E load() {
        Map<String, PathMap> pathMap = new HashMap<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> createQuery = criteriaBuilder.createQuery(entityType);
        Root<E> from = createQuery.from(entityType);
        Predicate[] predicate = getPredicate(this, from, pathMap, createQuery, criteriaBuilder);
        createQuery.where(predicate);
        createQuery.select(from).distinct(true);
        try {
            return entityManager.createQuery(createQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public <A extends Serializable, B extends BaseEntity<A>> Subquery<A, B> createSubquery(String property, Class<B> clazz) {
        return null;
    }

    /**
     * 获得跟组条件
     *
     * @param item            11
     * @param root            11
     * @param pathMap         11
     * @param criteriaBuilder 11
     * @return 11
     */
    protected List<Order> getOrder(Query<?, ?> item, Root<E> root, Map<String, PathMap> pathMap, CriteriaBuilder criteriaBuilder) {
        List<Order> predicates = new ArrayList<>();
        List<Condition> conditions2 = item.getOrders();

        for (Condition con : conditions2) {
            String property = con.getProperty();
            Path<Object> predicate = getPath(root, property, pathMap);
            Order order = null;
            if (con instanceof DescCondition) {
                order = criteriaBuilder.desc(predicate);
            } else if (con instanceof AscCondition) {
                order = criteriaBuilder.asc(predicate);
            }
            predicates.add(order);
        }
        return predicates;
    }

    /**
     * 获得path对象
     *
     * @param path     jpa path
     * @param property entity 属性
     * @param pathMap  path cache
     * @param <Y>      泛型对象类型
     * @return path对象
     */
    public <Y> Path<Y> getPath(Path<?> path, String property, Map<String, PathMap> pathMap) {
        String[] split = property.split("\\.");
        Iterator<String> iterator = Arrays.asList(split).iterator();
        return (Path<Y>) loadPath(iterator, path, pathMap);
    }

    public Path<?> loadPath(Iterator<String> iterator, Path<?> parentPath, Map<String, PathMap> pathMap) {
        String next = iterator.next();
        PathMap pathMap1 = pathMap.get(next);
        Path<?> objectPath;
        if (pathMap1 == null) {
            objectPath = parentPath.get(next);
            pathMap1 = new PathMap(next, objectPath);
            pathMap.put(next, pathMap1);
        } else {
            objectPath = pathMap1.getPath();
        }

        if (iterator.hasNext()) {
            return loadPath(iterator, objectPath, pathMap1.getChildren());
        } else {
            return objectPath;
        }
    }

    /**
     * @param item            query 对象
     * @param from            root
     * @param pathMap         path cache
     * @param countQuery      分页查询
     * @param criteriaBuilder 分页查询
     * @return 查询条件
     */

    protected Predicate[] getPredicate(Query<?, ?> item, Root<E> from, Map<String, PathMap> pathMap, CriteriaQuery<?> countQuery, CriteriaBuilder criteriaBuilder) {
        List<FilterCondition> conditions2 = item.getConditions();
        Predicate predicateResult;
        Predicate predicate = null;
        List<Predicate> predicates = new ArrayList<>();
        FilterType preType = FilterType.AND;
        for (FilterCondition con : conditions2) {
            String property = con.getProperty();
            if (con instanceof SimpleCondition) {
                OperationItem<?> item2 = ((SimpleCondition) con).getItem();
                predicate = operation2Predicate(property, item2, from, pathMap, criteriaBuilder);

            } else if (con instanceof IsNullCondition) {
                Path<Object> path = getPath(from, property, pathMap);
                predicate = criteriaBuilder.isNull(path);

            } else if (con instanceof IsNotNullCondition) {
                Path<Object> path = getPath(from, property, pathMap);
                predicate = criteriaBuilder.isNotNull(path);

            } else if (con instanceof InCondition) {
                Path<Object> path = getPath(from, property, pathMap);
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
                Object[] item2 = ((InCondition) con).getItem();
                for (Object o : item2) {
                    in.value(o);
                }
                predicate = in;
            } else if (con instanceof ListCondition) {
                Query<?, ?> item2 = ((ListCondition) con).getItem();
                Predicate[] predicateTemp = getPredicate(item2, from, pathMap, countQuery, criteriaBuilder);
                predicate = criteriaBuilder.and(predicateTemp);
            }
            FilterType type = con.getType();
            if (type == preType) {
                if (type == FilterType.AND) {
                    predicateResult = criteriaBuilder.and(predicate);
                } else {
                    predicateResult = criteriaBuilder.or(predicate);
                }
                predicates.add(predicateResult);
            } else {
                Predicate[] array = predicates.toArray(new Predicate[]{});
                Predicate temp;
                if (preType == FilterType.AND) {
                    predicateResult = criteriaBuilder.and(array);
                    temp = criteriaBuilder.or(predicateResult, predicate);
                } else {
                    predicateResult = criteriaBuilder.or(array);
                    temp = criteriaBuilder.and(predicateResult, predicate);
                }
                preType = type;
                predicates.clear();
                predicates.add(temp);
            }
        }
        return predicates.toArray(new Predicate[]{});
    }

    /**
     * @param property 属性
     * @param item2    操作项
     * @param from     root
     * @param pathMap  path cache
     * @return 查询条件
     */
//	@SuppressWarnings("unchecked")
    private Predicate operation2Predicate(String property, OperationItem<?> item2, Root<E> from, Map<String, PathMap> pathMap, CriteriaBuilder criteriaBuilder) {

        Operation operation = item2.getOperation();
        Object value = item2.getValue();

        String[] split = property.split("\\.");
        Operation2Predicate operation2Predicate = operation2PredicateFinder.find(operation);
        if (split.length > 1) {

            Path<Object> path = null;
            for (String s : split) {
                if (path == null) {
                    path = from.get(s);
                } else {
                    path = path.get(s);
                }
            }

            return operation2Predicate.full(criteriaBuilder, from, path, value);
        } else {
            Path<?> path = getPath(from, property, pathMap);
            return operation2Predicate.full(criteriaBuilder, from, path, value);
        }

    }

    protected List<Expression<?>> getGroups(Query<?, ?> item, Root<E> root, Map<String, PathMap> pathMap) {
        List<Expression<?>> result = new ArrayList<>();
        List<String> groups = getGroups();
        for (String s : groups) {
            result.add(getPath(root, s, pathMap));
        }
        return result;
    }

}
