package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.BillDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.List;


public class BillService extends ExtendedBaseService<Bill, BillDto> implements IBillService {

    private static BillService instance;
    private static BillDao billDao = BillDao.getInstance();

    private BillService() {
    }

    public static synchronized BillService getInstance() {
        if (instance == null) {
            instance = new BillService();
        }
        return instance;
    }

    @Override
    public List<BillDto> getByUserId(Long userId, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<BillDto> billDtoList = null;
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter("userId", userId);
        try {
            transaction.begin();
            List<Bill> billList = billDao.getListByFilter(searchFilter, page, perPage);
            billDtoList = converter.toDtoList(billList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

    @Deprecated
    public List<BillDto> getAll(int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<BillDto> billDtoList = null;
        try {
            transaction.begin();
            List<Bill> billList = billDao.getListByFilter(null, page, perPage);
            billDtoList = converter.toDtoList(billList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

}
