package by.kanarski.booking.newDao.interfaces;

import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Main dao interface. Represents base methods to database access
 * @author Dzmitry Kanarski
 * @version 1.0
 * @param <T> this is any of represented dao implementations
 */

public interface IDao<T> {

    /**
     * Appends entity in the database
     * @param t entity to be appended to database
     * @throws DaoException
     */

    void add(T t) throws DaoException;

    /**
     * Recives entity from databse by id
     * @param id the entity id
     * @return entity with the corresponding id
     * @throws DaoException
     */

    T getById(long id) throws DaoException;

    /**
     * Updates entity in database
     * @param t entity to be update
     * @throws DaoException
     */

    void update(T t) throws DaoException;

    /**
     * Deletes entity in database
     * @param t entity to be delete
     * @throws DaoException
     */

    void delete(T t) throws DaoException;
}
