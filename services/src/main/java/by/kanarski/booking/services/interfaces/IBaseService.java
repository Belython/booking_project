package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

public interface IBaseService<T> {

    void add(T t) throws ServiceException;

    T getById(Long id) throws ServiceException;

    void update(T t) throws ServiceException;

    void delete(T t) throws ServiceException;
}
