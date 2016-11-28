package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IExtendedDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import by.kanarski.booking.utils.wrappers.CriteriaConstraint;
import by.kanarski.booking.utils.wrappers.FilterElement;
import by.kanarski.booking.utils.wrappers.SearchFilter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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
        Criteria criteria = getCriteria(filter);
        criteria.setFirstResult(page);
        criteria.setMaxResults(perPage);
        return getResultList(criteria);
    }

    @Override
    public List<T> getListByFilter(SearchFilter filter) throws DaoException {
        Criteria criteria = getCriteria(filter);
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

    private Criteria getCriteria(SearchFilter filter) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        for (FilterElement filterElement : filter) {
            Criterion criterion = getCriterion(filterElement);
            criteria.add(criterion);
            if (filterElement.isAsc()) {
                criteria.addOrder(Order.asc(filterElement.getProperty()));
            } else {
                criteria.addOrder(Order.desc(filterElement.getProperty()));
            }
        }
        return criteria;
    }

    private Criterion getCriterion(FilterElement filterElement) {
        Criterion criterion = null;
        String property = filterElement.getProperty();
        CriteriaConstraint constraint = filterElement.getConstraint();
        Object value = filterElement.getValue();
        switch (constraint) {
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
            case LE: {
                criterion = Restrictions.lt(property, value);
                break;
            }
            case GE : {
                criterion = Restrictions.ge(property, value);
                break;
            }
            case LIKE: {
                criterion = Restrictions.like(property, value);
                break;
            }
            case ILIKE: {
                criterion = Restrictions.ilike(property, value);
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

    private List<T> getResultList(Criteria criteria) throws DaoException {
        List<T> resultList;
        try {
            resultList = criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return resultList;
    }

    private T getUniqueResult(Criteria criteria) throws DaoException {
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
