package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.List;

/**
 * Location dao iterface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */

public interface IExtendedDao<T> extends IBaseDao<T> {

    void updateList(List<T> tList) throws DaoException;

    void addList(List<T> tList) throws DaoException;

    List<T> getListByFilter(SearchFilter filter, int page, int perPage) throws DaoException;

    List<T> getListByFilter(SearchFilter filter) throws DaoException;

    T getUniqueByFilter(SearchFilter filter) throws DaoException;

    Long getResultsSize(SearchFilter filter, String searchProperty) throws DaoException;

}



