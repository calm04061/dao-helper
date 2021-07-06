package com.calm.dao.helper;

import com.calm.dao.helper.entity.BaseEntity;
import com.calm.dao.helper.entity.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * @param <I> id类型
 * @param <E> 实体类型
 */
public interface Query<I extends Serializable, E extends BaseEntity<I>> extends Subquery<I, E> {
    Long count();

    List<E> pagingList(int currentPage, int pageSize);

    /**
     * 分页查询
     *
     * @param currentPage 开始页
     * @param pageSize    每页记录数
     * @return 分页对象
     */
    Paging<E> paging(int currentPage, int pageSize);

    /**
     * 根据条件查询多条记录
     *
     * @return 结果集
     */
    List<E> list();

    /**
     * 根据条件查询单条记录
     *
     * @return 结果对象
     */
    E load();
}
