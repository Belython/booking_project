package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

public interface IBaseService<E, D> {

    void add(D t) throws ServiceException;

    D getById(Long id) throws ServiceException;

    void update(D t) throws ServiceException;

    void delete(D t) throws ServiceException;
}
