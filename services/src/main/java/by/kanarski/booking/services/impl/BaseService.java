package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBaseService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.lang.reflect.ParameterizedType;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

//@Service
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BaseService<E, D> implements IBaseService<E, D> {

//    @Autowired
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

    public void setEntityClass() {
        extendedBaseDao.setEntityClass(getEntityClass());
    }
}
