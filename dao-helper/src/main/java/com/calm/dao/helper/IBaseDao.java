package com.calm.dao.helper;

import com.calm.dao.helper.entity.BaseEntity;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author dingqihui
 */
public interface IBaseDao<I extends Serializable, E extends BaseEntity<I>, Q extends Query<I, E>> {

    /**
     * 根据ID查询对象
     *
     * @param id id
     * @return 实体类
     */
    E loadById(I id);


    /**
     * 插入对象
     *
     * @param entity 保存对象
     * @return 插入后的实体
     */
    @Transactional
    E insert(E entity);

    /**
     * 插入对象
     *
     * @param entity 保存对象
     * @return 插入后的实体
     */
    @Transactional
    void saveAll(List<E> entity);

    /**
     * 更新
     *
     * @param entity 保存对象
     * @return 更新后的对象
     */
    @Transactional
    E update(E entity);

    /**
     * 删除
     *
     * @param entity 对象
     */
    @Transactional
    void delete(List<E> entity);

    /**
     * 删除
     *
     * @param id 对象
     */
    @Transactional
    void deleteById(I id);

    /**
     * 创建查询对象
     *
     * @return 查询对象
     */
    Q query();

    /**
     * 根据查询语句查询
     *
     * @param jql  查询语句
     * @param args 参数
     * @return 查询结果集
     */
    List<E> listByQuery(String jql, Object... args);

    /**
     * 根据查询语句查询
     *
     * @param jql  查询语句
     * @param args 参数
     * @return 查询结果对象
     */
    E loadByQuery(String jql, Object... args);

    /**
     * 根据原生查询语句查询
     *
     * @param sql  查询原生语句
     * @param args 参数
     * @return 结果集
     */
    List<E> listByNativeQuery(String sql, Object... args);

    /**
     * 根据原生查询语句查询
     *
     * @param sql  查询原生语句
     * @param args 参数
     * @return 结果集
     */
    E loadByNativeQuery(String sql, Object... args);

    /**
     * 根据原生查询语句查询
     *
     * @param sql           查询原生语句
     * @param resultMapping 映射器
     * @param args          参数
     * @return 结果对象
     */
    E loadNativeQuery(String sql, String resultMapping, Object... args);

    /**
     * 根据原生查询语句查询
     *
     * @param sql           查询原生语句
     * @param resultMapping 映射器
     * @param args          参数
     * @return 结果对集合
     */
    List<E> listNativeQuery(String sql, String resultMapping, Object... args);

    Class<E> getEntityType();
}
