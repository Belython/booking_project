package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IExtendedDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import by.kanarski.booking.utils.wrappers.CriteriaRule;
import by.kanarski.booking.utils.wrappers.FilterElement;
import by.kanarski.booking.utils.wrappers.SearchFilter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ExtendedBaseDao<T> extends BaseDao<T> implements IExtendedDao<T> {

    private static final int BATCH_SIZE = 20;

    private Class<T> entityClass;

    public ExtendedBaseDao() {
    }

    public ExtendedBaseDao(Class<T> entityClass) {
        this.entityClass =  entityClass;
    }

    @Override
    public void updateList(List<T> entityList) throws DaoException {
        Session session = getSession();
        for (int i = 0; i < entityList.size(); i++) {
            T t = entityList.get(i);
            update(t);
            if (++i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public void addList(List<T> entityList) throws DaoException {
        Session session = getSession();
        for (int i = 0; i < entityList.size(); i++) {
            T t = entityList.get(i);
            add(t);
            if (++i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public List<T> getListByFilter(SearchFilter filter, int page, int perPage) throws DaoException {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        if (filter != null) {
            for (FilterElement filterElement : filter) {
                Criterion criterion = getCriterion(filterElement);
                criteria.add(criterion);
            }
        }
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return getResultList(criteria);
    }

    @Override
    public List<T> getListByFilter(SearchFilter filter) throws DaoException {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (FilterElement filterElement : filter) {
            Criterion criterion = getCriterion(filterElement);
            criteria.add(criterion);
        }
        paginateCriteria(criteria);
        return getResultList(criteria);
    }

    @Override
    public T getUniqueByFilter(SearchFilter filter) throws DaoException {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (FilterElement filterElement : filter) {
            Criterion criterion = getCriterion(filterElement);
            criteria.add(criterion);
        }
        return getUniqueResult(criteria);
    }

    @Override
    public Long getResultsSize(SearchFilter filter, String countProperty) throws DaoException {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (FilterElement filterElement : filter) {
            Criterion criterion = getCriterion(filterElement);
            criteria.add(criterion);
        }
        criteria.setProjection(Projections.count(countProperty));
        Long resultsSize;
        try {
            resultsSize = (Long) criteria.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return resultsSize;
    }

    private Criterion getCriterion(FilterElement filterElement) {
        Criterion criterion = null;
        String property = filterElement.getProperty();
        CriteriaRule rule = filterElement.getRule();
        Object value = filterElement.getValue();
        switch (rule) {
            case EQ: {
                criterion = Restrictions.eq(property, value);
                break;
            }
            case GT: {
                criterion = Restrictions.gt(property, value);
                break;
            }
            case LT: {
                criterion = Restrictions.lt(property, value);
                break;
            }
        }
        return criterion;
    }

    private void paginateCriteria(Criteria criteria) {
        int page = UserPreferences.getStartRow();
        int perPage = UserPreferences.getRowsPerPage();
        int firstResult = page * perPage;
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(perPage);
    }

    public List<T> getResultList(Criteria criteria) throws DaoException {
        List<T> resultList;
        try {
            resultList = criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return resultList;
    }

    public T getUniqueResult(Criteria criteria) throws DaoException {
        T result;
        try {
            result = (T) criteria.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    private Class<T> getEntityClass() {
        if (entityClass == null) {
            entityClass = getPersistentClass();
        }
        return entityClass;
    }

}
