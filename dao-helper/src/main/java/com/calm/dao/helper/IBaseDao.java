package com.calm.dao.helper;

import java.util.List;

/**
 * @author dingqihui
 */
public interface IBaseDao<I,E,Q extends Query<I, E>> {

	/**
	 *根据ID查询对象
	 * @param clazz 查询类型
	 * @param id id
	 * @return 实体类
	 */
	 E loadById(Class<E> clazz, I id);


	/**
	 * 插入对象
	 * @param entity 保存对象
	 * @return 插入后的实体
	 */
	 E insert(E entity);

	/**
	 * 更新
	 * @param entity 保存对象
	 * @return 更新后的对象
	 */
	 E update(E entity);

	/**
	 * 删除
	 * @param entities 对象
	 */
	 void delete(E... entities);

	/**
	 * 删除
	 * @param entity 对象
	 */
	 void delete(List<E> entity);

	/**
	 * 创建查询对象
	 * @param clazz 查询类型
	 * @return 查询对象
	 */
	Q query(Class<E> clazz);

	/**
	 * 根据查询语句查询
	 * @param jql 查询语句
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 查询结果集
	 */
	 List<E> listByQuery(String jql, Class<E> clazz, Object... args);

	/**
	 * 根据查询语句查询
	 * @param jql 查询语句
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 查询结果对象
	 */
	 E loadByQuery(String jql, Class<E> clazz, Object... args);

	/**
	 * 根据原生查询语句查询
	 * @param sql 查询原生语句
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 结果集
	 */
	 List<E> listByNativeQuery(String sql, Class<E> clazz, Object... args);

	/**
	 * 根据原生查询语句查询
	 * @param sql 查询原生语句
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 结果集
	 */
	 E loadByNativeQuery(String sql, Class<E> clazz, Object... args);

	/**
	 * 根据原生查询语句查询
	 * @param sql 查询原生语句
	 * @param mapper 映射器
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 结果对象
	 */
	 E loadNativeQuery(String sql, Mapper<E> mapper, Class<E> clazz, Object... args);

	/**
	 * 根据原生查询语句查询
	 * @param sql 查询原生语句
	 * @param mapper 映射器
	 * @param clazz 实体类类型
	 * @param args 参数
	 * @return 结果对集合
	 */
	List<E> listNativeQuery(String sql, Mapper<E> mapper, Class<E> clazz, Object... args);
	
}
