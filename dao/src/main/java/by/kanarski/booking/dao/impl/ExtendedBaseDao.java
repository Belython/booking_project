package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.filter.CriteriaConstraint;
import by.kanarski.booking.utils.filter.DisjunctionElement;
import by.kanarski.booking.utils.filter.FilterElement;
import by.kanarski.booking.utils.filter.SearchFilter;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Repository
public class ExtendedBaseDao<T> extends BaseDao<T> implements IExtendedBaseDao<T> {

    private static final int BATCH_SIZE = 20;
    private static final List<Class> CACHEABLE_ENTITIES = Arrays.asList(Location.class, Hotel.class, RoomType.class,
            Facility.class, LocationTranslation.class, HotelTranslation.class, RoomTypeTranslation.class,
            FacilityTranslation.class);

    private Class<T> entityClass;

    @Autowired
    public ExtendedBaseDao(SessionFactory sessionFactory) {
        super(sessionFactory);
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
    public List<T> getListByCriteria(Criteria criteria, int page, int perPage) throws DaoException {
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
        Criteria criteria = getCriteria(filter);
        return getUniqueResult(criteria);
    }

    protected Long getCountByCriteria(Criteria criteria) throws HibernateException {
//        criteria.setProjection(Projections.distinct(Projections.rowCount()));
        return (Long) criteria.uniqueResult();
    }

    @Override
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
//    @Override
//    public Long getResultsSize(SearchFilter filter, String countProperty) throws DaoException {
//        Criteria criteria = getSession().createCriteria(getEntityClass());
//        for (FilterElement filterElement : filter) {
//            Criterion criterion = getCriterion(filterElement);
//            criteria.add(criterion);
//        }
//        criteria.setProjection(Projections.count(countProperty));
//        Long resultsSize;
//        try {
//            resultsSize = (Long) criteria.uniqueResult();
//        } catch (HibernateException e) {
//            throw new DaoException(e.getMessage(), e);
//        }
//        return resultsSize;
//    }

    private void addSimpleFilters(Criteria criteria, SearchFilter filter) {
        List<FilterElement> filterElementList = filter.getFilterList();
        for (FilterElement filterElement : filterElementList) {
            Criterion criterion = getCriterion(filterElement);
            criteria.add(criterion);
        }
    }

    private void addDusjunctions(Criteria criteria, SearchFilter filter) {
        List<DisjunctionElement> disjunctionElementList = filter.getDisjunctions();
        for (DisjunctionElement disjunctionElement : disjunctionElementList) {
            Disjunction disjunction = getDisjunction(disjunctionElement);
            criteria.add(disjunction);
        }
    }

    private Criteria getCriteria(SearchFilter filter) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        if (CACHEABLE_ENTITIES.contains(entityClass)) {
            criteria.setCacheable(true);
        }
        if (filter != null) {
            addAliases(criteria, filter);
            addSimpleFilters(criteria, filter);
            addDusjunctions(criteria, filter);
        }
        return criteria;
    }

    private Criterion getCriterion(FilterElement filterElement) {
        Criterion criterion = null;
        String property = filterElement.getProperty();
        CriteriaConstraint constraint = filterElement.getConstraint();
        Object value1 = filterElement.getValue1();
        Object value2 = filterElement.getValue2();
        switch (constraint) {
            case EQ: {
                criterion = Restrictions.eq(property, value1);
                break;
            }
            case GT: {
                criterion = Restrictions.gt(property, value1);
                break;
            }
            case LT: {
                criterion = Restrictions.lt(property, value1);
                break;
            }
            case LE: {
                criterion = Restrictions.lt(property, value1);
                break;
            }
            case GE : {
                criterion = Restrictions.ge(property, value1);
                break;
            }
            case LIKE: {
                criterion = Restrictions.like(property, value1);
                break;
            }
            case ILIKE: {
                criterion = Restrictions.ilike(property, value1);
                break;
            }
            case BEETWEN: {
                criterion = Restrictions.between(property, value1, value2);
                break;
            }
        }
        return criterion;
    }

    private Disjunction getDisjunction(DisjunctionElement disjunctionElement) {
        Disjunction disjunction = Restrictions.disjunction();
        List<FilterElement> filterElementList = disjunctionElement.getFilterList();
        for (FilterElement filterElement : filterElementList) {
            Criterion criterion = getCriterion(filterElement);
            disjunction.add(criterion);
        }
        return disjunction;
    }

    private void addAliases(Criteria criteria, SearchFilter filter) {
        Set<String> aliasNames = filter.getAliasNames();
        for (String aliasName : aliasNames) {
            criteria.createAlias(aliasName, filter.getAlias(aliasName));
        }
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
