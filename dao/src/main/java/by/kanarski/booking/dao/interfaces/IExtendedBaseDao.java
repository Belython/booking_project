package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Extended bsae dao interface. Provides additional methods for simple operation with a database
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */
public interface IExtendedBaseDao<T> extends IBaseDao<T> {

    /**
     * Updates list of entities
     * @param tList list of entities to update
     * @throws DaoException
     */
    void updateList(List<T> tList) throws DaoException;


    /**
     * Adds list of entities
     * @param tList list of entities to add
     * @throws DaoException
     */
    void addList(List<T> tList) throws DaoException;

    /**
     * Recives list of entities by some predefined criteria. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param criteria predefined criteria for search
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of etities
     * @throws DaoException
     */
    List<T> getListByCriteria(Criteria criteria, int page, int perPage) throws DaoException;

    /**
     * Recives list of entities by some predefined filter. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param filter predefined filter for search
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of etities
     * @throws DaoException
     * @see SearchFilter
     */
    List<T> getListByFilter(SearchFilter filter, int page, int perPage) throws DaoException;

    /**
     * Recives only one entity by some predefined filter
     * @param filter predefined filter for search
     * @return required entity
     * @throws DaoException
     */
    T getUniqueByFilter(SearchFilter filter) throws DaoException;

    /**
     * Recives all entities from table. Not recommended for use
     * @return all entities
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * Defines current entity for DAO
     * @param entityClass current entity class
     */
    void setEntityClass(Class<T> entityClass);

}



