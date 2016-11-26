package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.ExtendedBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBaseService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;

import java.lang.reflect.ParameterizedType;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class BaseService<E, D> implements IBaseService<D> {

    protected ExtendedBaseDao<E> extendedBaseDao =  new ExtendedBaseDao<E>(getEntityClass());
    protected DtoToEntityConverter<E, D> converter = new DtoToEntityConverter<>(getEntityClass(), getDtoClass());

    @Override
    public void add(D dto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            E entity = converter.toEntity(dto);
            extendedBaseDao.add(entity);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public D getById(Long id) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        D dto = null;
        try {
            transaction.begin();
            E entity = extendedBaseDao.getById(id);
            dto = converter.toDto(entity);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return dto;
    }

    @Override
    public void update(D dto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            E entity = converter.toEntity(dto);
            extendedBaseDao.update(entity);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void delete(D dto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            E entity = converter.toEntity(dto);
            extendedBaseDao.delete(entity);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    protected Class<E> getEntityClass() {
        ParameterizedType classType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> persistentClass = (Class<E>) classType.getActualTypeArguments()[0];
        return persistentClass;
    }

    protected Class<D> getDtoClass() {
        ParameterizedType classType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<D> persistentClass = (Class<D>) classType.getActualTypeArguments()[1];
        return persistentClass;
    }
}
