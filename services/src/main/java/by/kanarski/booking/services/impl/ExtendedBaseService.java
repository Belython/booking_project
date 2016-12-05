package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IExtendedBaseService;
import by.kanarski.booking.utils.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExtendedBaseService<E, D> extends BaseService<E, D> implements IExtendedBaseService<E, D> {

    @Autowired
    private IExtendedBaseDao<E> extendedBaseDao;

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

}
