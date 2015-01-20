package com.ect.bigjump.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 * 持久层基础泛型接口,用于其他持久层接口的继承
 *
 * @param <T>
 * @param <ID>
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-01
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * 保存新增或修改的对象
     *
     * @param entity
     */
    void save(final T entity);

    /**
     * 删除对象
     *
     * @param entity
     */
    void delete(final T entity);

    /**
     * 根据主键删除对象
     *
     * @param id
     */
    void delete(final ID id);

    /**
     * 根据主键查询对象
     *
     * @param id
     * @return
     */
    T get(final ID id);

    /**
     * 根据主键集合查询对象集合
     *
     * @param ids
     * @return
     */
    List<T> get(final Collection<ID> ids);

    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> getAll();

    /**
     * 根据属性进行排序查询所有对象
     *
     * @param orderByProperty
     * @param isAsc
     * @return
     */
    List<T> getAll(String orderByProperty, boolean isAsc);

    /**
     * 根据属性查询对象
     *
     * @param propertyName
     * @param value
     * @return
     */
    List<T> findBy(final String propertyName, final Object value);

    /**
     * 根据属性查询唯一对象
     *
     * @param propertyName
     * @param value
     * @return
     */
    T findUniqueBy(final String propertyName, final Object value);

    /**
     * 使用sql查询对象集合
     *
     * @param sql
     * @param params
     * @return
     */
    List<T> findBySql(String sql, Object[] params);

    /**
     * 使用hql查询对象集合
     *
     * @param hql
     * @param params
     * @return
     */
    List<T> findByHql(String hql, Object[] params);

    /**
     * 返回SQLQuery对象,用于sql的自定义查询
     *
     * @param sqlQueryString
     * @param values
     * @return
     */
    SQLQuery createSQLQuery(final String sqlQueryString, final Object... values);


}
