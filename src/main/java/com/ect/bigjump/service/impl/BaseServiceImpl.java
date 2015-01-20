package com.ect.bigjump.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.service.BaseService;

/**
 * 服务层基础泛型实现类,抽象类
 *
 * @param <T>
 * @param <ID>
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 值类型集合
     */
    protected static final String[] DATA_TYPES = {"STRING", "LONG", "DOUBLE"};

    public abstract BaseDao<T, ID> getDao();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(T entity) throws Exception {
        Method setCreationDateMethod = entity.getClass().getMethod("setCreationDate", Date.class);
        setCreationDateMethod.invoke(entity, new Date());

        Method setLastUpdateDateMethod = entity.getClass().getMethod("setLastUpdateDate", Date.class);
        setLastUpdateDateMethod.invoke(entity, new Date());
        getDao().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(T entity) throws Exception {
        Method setLastUpdateDateMethod = entity.getClass().getMethod("setLastUpdateDate", Date.class);
        setLastUpdateDateMethod.invoke(entity, new Date());
        getDao().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(T entity) throws Exception {
        getDao().delete(entity);
    }

    @Override
    @Transactional
    public T get(ID id) {
        return getDao().get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(ID id) throws Exception {
        getDao().delete(id);
    }

    @Override
    @Transactional
    public List<T> getAll() {
        return getDao().getAll();
    }

}
