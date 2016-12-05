package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BillService extends ExtendedBaseService<Bill, BillDto> implements IBillService {

    @Autowired
    private IBillDao billDao;

    @Override
    public List<BillDto> getByUserId(Long userId, int page, int perPage) throws ServiceException {
        List<BillDto> billDtoList = null;
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.USER_ID, userId);
        try {
            List<Bill> billList = billDao.getListByFilter(searchFilter, page, perPage);
            billDtoList = converter.toDtoList(billList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

    @Deprecated
    public List<BillDto> getAll(int page, int perPage) throws ServiceException {
        List<BillDto> billDtoList = null;
        try {
            List<Bill> billList = billDao.getListByFilter(null, page, perPage);
            billDtoList = converter.toDtoList(billList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

}
