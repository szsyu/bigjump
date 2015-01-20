package com.ect.bigjump.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.utility.ReflectUtils;

/**
 * 持久层基础泛型类,用于实体持久层类的继承
 *
 * @param <T>
 * @param <ID>
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public abstract class BaseDaoImpl<T, ID extends Serializable> implements
        BaseDao<T, ID> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public BaseDaoImpl() {
        this.entityClass = ReflectUtils.getClassGenricType(getClass());
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 获取当前Session
     *
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void delete(ID id) {
        delete(get(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(ID id) {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public List<T> get(Collection<ID> ids) {
        return find(Restrictions.in(getIdName(), ids));
    }

    @Override
    public List<T> getAll() {
        return find();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll(String orderByProperty, boolean isAsc) {
        Criteria c = createCriteria();
        if (isAsc) {
            c.addOrder(Order.asc(orderByProperty));
        } else {
            c.addOrder(Order.desc(orderByProperty));
        }
        return c.list();
    }

    @Override
    public List<T> findBy(String propertyName, Object value) {
        Criterion criterion = Restrictions.eq(propertyName, value);
        return find(criterion);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findUniqueBy(String propertyName, Object value) {
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T) createCriteria(criterion).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findBySql(String sql, Object[] params) {
        Query query = (Query) sessionFactory.getCurrentSession()
                .createSQLQuery(sql).addEntity(entityClass);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByHql(String hql, Object[] params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = (Query) sessionFactory.getCurrentSession().createQuery(
                hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }


    @Override
    public SQLQuery createSQLQuery(final String sqlQueryString, final Object... values) {
        SQLQuery query = getSession().createSQLQuery(sqlQueryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }


    /**
     * 取得对象的主键名.
     */
    public String getIdName() {
        ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
        return meta.getIdentifierPropertyName();
    }

    /**
     * 按Criteria查询对象列表.
     *
     * @param criterions 数量可变的Criterion.
     */
    @SuppressWarnings("unchecked")
    public List<T> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 根据Criterion条件创建Criteria.
     * 与find()函数可进行更加灵活的操作.
     *
     * @param criterions 数量可变的Criterion.
     */
    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }
}
