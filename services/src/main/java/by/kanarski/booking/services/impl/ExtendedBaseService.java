package by.kanarski.booking.services.impl;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IExtendedBaseService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ExtendedBaseService<E, D> extends BaseService<E, D> implements IExtendedBaseService<D> {


    @Override
    public void updateList(List<D> dtoList) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.updateList(entityList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void addList(List<D> dtoList) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.addList(entityList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Deprecated
    public List<D> getAll(int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<D> dtoList = null;
        try {
            transaction.begin();
            List<E> entityList = extendedBaseDao.getListByFilter(null, page, perPage);
            dtoList = converter.toDtoList(entityList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return dtoList;
    }

}
