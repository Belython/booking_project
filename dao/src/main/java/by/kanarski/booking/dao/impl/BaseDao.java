package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Repository
public class BaseDao<T> implements IBaseDao<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(T t) throws DaoException {
        getSession().save(t);
    }

    @Override
    public T getById(Serializable id) throws DaoException {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public void update(T t) throws DaoException {
        getSession().update(t);
    }

    @Override
    public void delete(T t) throws DaoException {
        getSession().delete(t);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> getPersistentClass() {
        ParameterizedType classType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> persistentClass = (Class<T>) classType.getActualTypeArguments()[0];
        return persistentClass;
    }
}
