package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.StateValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.forms.BookRoomsForm;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BillUtil;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BillService extends ExtendedBaseService<Bill, BillDto> implements IBillService {

    @Autowired
    private IBillDao billDao;

    @Autowired
    private IUserHotelService userHotelService;

    @Override
    public List<BillDto> getByUserId(Long userId, int page, int perPage) throws ServiceException {
        List<BillDto> billDtoList = null;
        SearchFilter searchFilter = SearchFilter.createAliasFilter(AliasName.USER, AliasValue.USER)
                .addAlias(AliasName.STATUS, AliasValue.STATUS)
                .addEqFilter(SearchParameter.USER_USERIDID, userId)
                .addNeFilter(SearchParameter.STATUS_STATENAME, StateValue.STATUS_DELETED);
        try {
            List<Bill> billList = billDao.getListByFilter(searchFilter, page, perPage);
            billDtoList = conversionService.convert(billList, BillDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return billDtoList;
    }

    @Override
    public void makeBill(BookRoomsForm bookRoomsForm, OrderDto orderDto) throws ServiceException {
        HotelDto hotelDto = conversionService.convert(orderDto.getUserHotelDto(), HotelDto.class);
        UserHotelDto userHotelDto = userHotelService.getById(hotelDto.getHotelId());
        List<RoomDto> allRooms = userHotelDto.getRoomList();
        List<RoomDto> bookedRooms = BillUtil.chooseRoomsForBooking(bookRoomsForm, allRooms);
        try {
            Double paymentAmount = BillUtil.getPaymentAmount(orderDto.getCheckInDate(), orderDto.getCheckOutDate(), bookedRooms);
            UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String bookingDate = DateUtil.getFormattedDate(new Date().getTime());
            BillDto billDto = new BillDto(user, bookingDate, orderDto.getTotalPersons(), orderDto.getCheckInDate(),
                    orderDto.getCheckOutDate(), hotelDto, bookedRooms, paymentAmount);
            add(billDto);
        } catch (LocalisationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void cancelBooking(Long billId) throws ServiceException {
        try {
            Bill bill = billDao.getById(billId);
            bill.getPaymentStatus().setStateName(StateValue.STATUS_CANCELED);
            billDao.update(bill);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public void payBIll(Long billId) throws ServiceException {
        try {
            Bill bill = billDao.getById(billId);
            bill.getPaymentStatus().setStateName(StateValue.STATUS_PAID);
            billDao.update(bill);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    public void deleteBill(Long billId) throws ServiceException {
        try {
            Bill bill = billDao.getById(billId);
            bill.getStatus().setStateName(StateValue.STATUS_DELETED);
            billDao.update(bill);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
    }

    @Override
    protected IExtendedBaseDao<Bill> getDao() {
        return billDao;
    }

    //    @Deprecated
//    public List<BillDto> getAll(int page, int perPage) throws ServiceException {
//        List<BillDto> billDtoList = null;
//        try {
//            List<Bill> billList = billDao.getListByFilter(null, page, perPage);
//            billDtoList = converter.toDtoList(billList);
//        } catch (DaoException e) {
//            BookingExceptionHandler.handleDaoException(e);
//        } catch (LocalisationException e) {
//            BookingExceptionHandler.handleLocalizationException(e);
//        }
//        return billDtoList;
//    }

}
