package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IExtendedBaseService<E, D> {

    void add(D t) throws ServiceException;

    D getById(Long id) throws ServiceException;

    void update(D t) throws ServiceException;

    void delete(D t) throws ServiceException;

    void updateList(List<D> tList) throws ServiceException;

    void addList(List<D> tList) throws ServiceException;

}
