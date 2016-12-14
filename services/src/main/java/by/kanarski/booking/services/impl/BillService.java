package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.forms.BookRoomsForm;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.roomType.OrderedRoomTypesDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.USER_ID, userId);
        try {
            List<Bill> billList = billDao.getListByFilter(searchFilter, page, perPage);
            billDtoList = converter.toDtoList(billList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

    public void makeBill(BookRoomsForm bookRoomsForm, HotelDto hotelDto, OrderDto orderDto) throws ServiceException {
        UserHotelDto userHotelDto = userHotelService.getById(hotelDto.getHotelId());
        List<RoomDto> bookedRooms = new ArrayList<>();
        List<RoomDto> allRooms = userHotelDto.getRoomList();
        List<OrderedRoomTypesDto> orderedRoomTypesDtos = bookRoomsForm.getOrderedRooms();
        for (RoomDto roomDto : allRooms) {
            Long roomTypeId = roomDto.getRoomType().getRoomTypeId();
            for (OrderedRoomTypesDto orderedRoomTypesDto : orderedRoomTypesDtos) {
                Long bookedRoomTypeId = orderedRoomTypesDto.getRoomTypeId();
                if (roomTypeId.equals(bookedRoomTypeId)) {
                    bookedRooms.add(roomDto);
                }
            }
        }
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BillDto billDto = new BillDto(user, orderDto.getTotalPersons(), orderDto.getCheckInDate(),
                orderDto.getCheckOutDate(), hotelDto, bookedRooms, 1000D);
        add(billDto);
    }

    @Deprecated
    public List<BillDto> getAll(int page, int perPage) throws ServiceException {
        List<BillDto> billDtoList = null;
        try {
            List<Bill> billList = billDao.getListByFilter(null, page, perPage);
            billDtoList = converter.toDtoList(billList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return billDtoList;
    }

}
