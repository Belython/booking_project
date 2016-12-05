package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IExtendedBaseService<E, D> {

    void updateList(List<D> tList) throws ServiceException;

    void addList(List<D> tList) throws ServiceException;

}
