package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IExtendedBaseService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExtendedBaseService<E, D> implements IExtendedBaseService<E, D> {

    @Autowired
    private IExtendedBaseDao<E> extendedBaseDao;

    @Autowired
    protected IEntityConversionService conversionService;

    @Override
    public void add(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = conversionService.convert(dto, getEntityClass());
            extendedBaseDao.add(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public D getById(Long id) throws ServiceException {
        setEntityClass();
        D dto = null;
        try {
            E entity = extendedBaseDao.getById(id);
            dto = conversionService.convert(entity, getDtoClass());
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return dto;
    }

    @Override
    public void update(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = conversionService.convert(dto, getEntityClass());
            extendedBaseDao.update(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public void delete(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = conversionService.convert(dto, getEntityClass());
            extendedBaseDao.delete(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public List<D> getAll() throws ServiceException {
        setEntityClass();
        List<D> dtoList = null;
        try {
            List<E> enittyList = extendedBaseDao.getAll();
            dtoList = conversionService.convert(enittyList, getDtoClass());
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return dtoList;
    }


    @Override
    public void updateList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = conversionService.convert(dtoList, getEntityClass());
            extendedBaseDao.updateList(entityList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public void addList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = conversionService.convert(dtoList, getEntityClass());
            extendedBaseDao.addList(entityList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    private void setEntityClass() {
//        getConverter();
        extendedBaseDao.setEntityClass(getEntityClass());
    }


    protected Class<E> getEntityClass() {
        Type superclass = getClass().getGenericSuperclass();
        Class<E> persistentClass = null;
        if (!superclass.equals(Object.class)) {
            ParameterizedType classType = (ParameterizedType) superclass;
            persistentClass = (Class<E>) classType.getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    protected Class<D> getDtoClass() {
        Type superclass = getClass().getGenericSuperclass();
        Class<D> persistentClass = null;
        if (!superclass.equals(Object.class)) {
            ParameterizedType classType = (ParameterizedType) superclass;
            persistentClass = (Class<D>) classType.getActualTypeArguments()[1];
        }
        return persistentClass;
    }

}
