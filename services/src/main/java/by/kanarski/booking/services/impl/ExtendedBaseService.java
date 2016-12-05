package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IExtendedBaseService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
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

    protected DtoToEntityConverter<E, D> converter = new DtoToEntityConverter<>(getEntityClass(), getDtoClass());

    @Override
    public void add(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.add(entity);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public D getById(Long id) throws ServiceException {
        setEntityClass();
        D dto = null;
        try {
            E entity = extendedBaseDao.getById(id);
            dto = converter.toDto(entity);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return dto;
    }

    @Override
    public void update(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.update(entity);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void delete(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.delete(entity);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void updateList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.updateList(entityList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void addList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.addList(entityList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

    @Deprecated
    public List<D> getAll(int page, int perPage) throws ServiceException {
        setEntityClass();
        List<D> dtoList = null;
        try {
            List<E> entityList = extendedBaseDao.getListByFilter(null, page, perPage);
            dtoList = converter.toDtoList(entityList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return dtoList;
    }

    private void setEntityClass() {
        extendedBaseDao.setEntityClass(getEntityClass());
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
