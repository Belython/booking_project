package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IExtendedBaseService<T> {

    void updateList(List<T> tList) throws ServiceException;

    void addList(List<T> tList) throws ServiceException;

}
