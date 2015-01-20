package com.ect.bigjump.service;

import java.io.Serializable;
import java.util.List;

/**
 * 服务层基础泛型接口,用于其他实体服务接口的继承
 *
 * @param <T>
 * @param <ID>
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 新增对象
     *
     * @param entity
     * @throws Exception
     */
    void add(T entity) throws Exception;

    /**
     * 更新对象
     *
     * @param entity
     * @throws Exception
     */
    void update(T entity) throws Exception;

    /**
     * 删除对象
     *
     * @param entity
     * @throws Exception
     */
    void delete(T entity) throws Exception;

    /**
     * 根据主键id查询对象
     *
     * @param id
     * @return
     */
    T get(ID id);

    /**
     * 根据主键id删除对象
     *
     * @param id
     * @throws Exception
     */
    void deleteById(ID id) throws Exception;

    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> getAll();
}
