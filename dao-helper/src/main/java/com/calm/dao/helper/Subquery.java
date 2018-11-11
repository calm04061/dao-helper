package com.calm.dao.helper;

import com.calm.dao.helper.condition.Condition;
import com.calm.dao.helper.condition.filter.FilterCondition;
import com.calm.dao.helper.condition.filter.MatchType;
import com.calm.dao.helper.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 子查询
 *
 * @param <I> id
 * @param <E> 实体
 */
public interface Subquery<I extends Serializable, E extends BaseEntity<I>> {

    /**
     * 多个or条件
     *
     * @param property 排序条件
     * @return 查询器
     */
    Query<I, E> desc(String property);

    /**
     * 多个or条件
     *
     * @param property 排序条件
     * @return 查询器
     */
    Query<I, E> asc(String property);

    /**
     * 判断相等
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> eq(String property, Object value);

    /**
     * 范围
     *
     * @param property 属性
     * @param start    开始值
     * @param end    结束值
     * @return 查询器
     */
    Query<I, E> between(String property, Comparable start, Comparable end);

    /**
     * 存在
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> in(String property, Object[] value);

    /**
     * 存在
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> in(String property, List<?> value);

    /**
     * 大于等于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> ge(String property, Comparable<?> value);

    /**
     * 大于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> gt(String property, Comparable<?> value);

    /**
     * 小于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> lt(String property, Comparable<?> value);

    /**
     * 小于等于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> le(String property, Comparable<?> value);

    /**
     * 属性为空
     *
     * @param property 属性
     * @return 查询器
     */
    Query<I, E> isNull(String property);

    /**
     * 属性非空
     *
     * @param property 属性
     * @return 查询器
     */
    Query<I, E> notNull(String property);

    /**
     * 范围
     *
     * @param property 属性
     * @param start    开始值
     * @param end    结束值
     * @return 查询器
     */
    Query<I, E> orBetween(String property, Comparable start,Comparable end);


    /**
     * 模糊匹配(默认包含)
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> like(String property, String value);

    /**
     * 根据匹配模式模糊匹配
     *
     * @param property  属性
     * @param value     过滤值
     * @param matchType 匹配类型
     * @return 查询器
     */
    Query<I, E> like(String property, String value, MatchType matchType);

    /**
     * 或者相等
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orEq(String property, Object value);

    /**
     * 大于等于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orGe(String property, Comparable<?> value);

    /**
     * 大于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orGt(String property, Comparable<?> value);

    /**
     * 小于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orLt(String property, Comparable<?> value);

    /**
     * 小于等于
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orLe(String property, Comparable<?> value);

    /**
     * 属性为空
     *
     * @param property 属性
     * @return 查询器
     */
    Query<I, E> orIsNull(String property);

    /**
     * 属性非空
     *
     * @param property 属性
     * @return 查询器
     */
    Query<I, E> orNotNull(String property);

    /**
     * 模糊匹配(默认包含)
     *
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Query<I, E> orLike(String property, String value);

    /**
     * 根据匹配模式模糊匹配
     *
     * @param property  属性
     * @param value     过滤值
     * @param matchType 匹配类型
     * @return 查询器
     */
    Query<I, E> orLike(String property, String value, MatchType matchType);


    /**
     * 获得条件
     *
     * @return 过滤条件
     */
    List<FilterCondition> getConditions();

    /**
     * 获得排序条件
     *
     * @return 排序集合
     */
    List<Condition> getOrders();

    /**
     * 获得分组条件
     *
     * @return 分组集合
     */
    List<String> getGroups();

    /**
     * @param property 属性
     * @param value    过滤值
     * @return 查询器
     */
    Subquery<I, E> orIn(String property, Object[] value);

    /**
     * @param property 属性
     * @param clazz    子查询器
     * @param <A>     子查询ID
     * @param <B>     子查询实体
     * @return 子查询
     */
    <A extends Serializable, B extends BaseEntity<A>> Subquery<A, B> createSubquery(String property, Class<B> clazz);

    /**
     * 非逻辑删除
     * @return 查询器
     */
    Query<I, E> normal();

    /**
     * 且不相等
     * @param property 属性
     * @param value 值
     * @return 查询器
     */
    Query<I, E> ne(String property, Object value);

}
