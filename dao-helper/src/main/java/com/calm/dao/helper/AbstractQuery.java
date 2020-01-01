package com.calm.dao.helper;

import com.calm.dao.helper.condition.*;
import com.calm.dao.helper.condition.filter.FilterType;
import com.calm.dao.helper.condition.filter.InCondition;
import com.calm.dao.helper.condition.filter.MatchType;
import com.calm.dao.helper.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.calm.dao.helper.condition.filter.FilterType.AND;
import static com.calm.dao.helper.condition.filter.FilterType.OR;

public abstract class AbstractQuery<I extends Serializable, E extends BaseEntity<I>> implements Query<I, E> {
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractQuery.class);
    private List<FilterCondition> conditions = new ArrayList<>();

    private List<PropertyCondition> orders = new ArrayList<>();

    private List<String> groups = new ArrayList<>();

    /* (non-Javadoc)
     * @see com.calm.framework.common.dao.ObjectQuery#eq(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> eq(String property, Object value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.eq(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orEq(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> orEq(String property, Object value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.eq(OR, property, value));
        return this;
    }


    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#like(java.lang.String, java.lang.String)
     */
    @Override
    public Query<I, E> like(String property, String value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.like(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#like(java.lang.String, java.lang.String, com.aixuexi.common.condition.MatchType)
     */
    @Override
    public Query<I, E> like(String property, String value, MatchType matchType) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        StringBuilder temp = toMatchValue(value, matchType);
        conditions.add(Conditions.like(AND, property, temp.toString()));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#isNull(java.lang.String)
     */
    @Override
    public Query<I, E> isNull(String property) {
        conditions.add(Conditions.isNull(FilterType.AND, property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#notNull(java.lang.String)
     */
    @Override
    public Query<I, E> notNull(String property) {
        conditions.add(Conditions.isNotNull(FilterType.AND, property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#ge(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> ge(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.ge(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#gt(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> gt(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.gt(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#lt(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> lt(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.lt(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#le(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> le(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.le(AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orGe(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> orGe(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.ge(OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orGt(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> orGt(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.gt(OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orLt(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> orLt(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.lt(OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orLe(java.lang.String, java.lang.Object)
     */
    @Override
    public Query<I, E> orLe(String property, Comparable<?> value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.le(OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orIsNull(java.lang.String)
     */
    @Override
    public Query<I, E> orIsNull(String property) {
        conditions.add(Conditions.isNull(FilterType.OR, property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orNotNull(java.lang.String)
     */
    @Override
    public Query<I, E> orNotNull(String property) {
        conditions.add(Conditions.isNotNull(FilterType.OR, property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orLike(java.lang.String, java.lang.String)
     */
    @Override
    public Query<I, E> orLike(String property, String value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.like(OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#orLike(java.lang.String, java.lang.String, com.aixuexi.common.condition.MatchType)
     */
    @Override
    public Query<I, E> orLike(String property, String value, MatchType matchType) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        StringBuilder temp = toMatchValue(value, matchType);
        conditions.add(Conditions.like(OR, property, temp.toString()));
        return this;
    }

    private StringBuilder toMatchValue(String value, MatchType matchType) {
        StringBuilder temp = new StringBuilder();
        switch (matchType) {
            case START_WITH:
                temp.append(value);
                temp.append("%");
                break;
            case END_WITH:
                temp.append("%");
                temp.append(value);
                break;
            case CONTENT:
                temp.append("%");
                temp.append(value);
                temp.append("%");
                break;

            default:
                break;
        }
        return temp;
    }

    @Override
    public Query<I, E> desc(String property) {
        orders.add(new DescCondition(property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#in(java.lang.String, java.lang.Object[])
     */
    @Override
    public Query<I, E> in(String property, Object[] value) {
        if (value == null) {
            LOGGER.info(" value is null,be ignored");
            return this;
        }
        conditions.add(new InCondition(FilterType.AND, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#in(java.lang.String, java.lang.Object[])
     */
    @Override
    public Query<I, E> in(String property, List<?> value) {
        if (value == null) {
            LOGGER.info(" value is null,be ignored");
            return this;
        }
        conditions.add(new InCondition(FilterType.AND, property, value.toArray()));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#in(java.lang.String, java.lang.Object[])
     */
    @Override
    public Query<I, E> orIn(String property, Object[] value) {
        conditions.add(new InCondition(FilterType.OR, property, value));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#in(java.lang.String, java.lang.Object[])
     */
    @Override
    public Query<I, E> orIn(String property, List<?> value) {
        conditions.add(new InCondition(FilterType.OR, property, value.toArray()));
        return this;
    }

    @Override
    public Query<I, E> ne(String property, Object value) {
        if (isBlankString(value)) {
            LOGGER.info(" value is empty,be ignored");
            return this;
        }
        conditions.add(Conditions.ne(FilterType.AND, property, value));
        return this;
    }

    @Override
    public Query<I, E> normal() {
        conditions.add(Conditions.ne(FilterType.AND, Constants.LOGISTIC_DELETABLE_PROPERTY_NAME, true));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#asc(java.lang.String)
     */
    @Override
    public Query<I, E> asc(String property) {
        orders.add(new AscCondition(property));
        return this;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#getOrders()
     */
    @Override
    public List<PropertyCondition> getOrders() {
        return orders;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#getGroups()
     */
    @Override
    public List<String> getGroups() {
        return groups;
    }

    /* (non-Javadoc)
     * @see com.aixuexi.common.dao.ObjectQuery#getConditions()
     */
    @Override
    public List<FilterCondition> getConditions() {
        return conditions;
    }

    private boolean isEmptyString(Object value) {
        return value instanceof String && ((String) value).trim().length() == 0;
    }

    private boolean isBlankString(Object value) {
        if (value == null) {
            return true;
        }
        return isEmptyString(value);
    }

    @Override
    public Query<I, E> between(String property, Comparable<?> start, Comparable<?> end) {
        if (start == null) {
            return this;
        }
        if (end == null) {
            return this;
        }
        conditions.add(Conditions.between(AND, property, start, end));
        return this;
    }

    @Override
    public Query<I, E> orBetween(String property, Comparable<?> start, Comparable<?> end) {
        if (start == null) {
            return this;
        }
        if (end == null) {
            return this;
        }
        conditions.add(Conditions.between(OR, property, start, end));
        return this;
    }
}
