package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.SearchOrder;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserHotelService extends ExtendedBaseService<Hotel, UserHotelDto> implements IUserHotelService {

    @Autowired
    private IHotelDao hotelDao;

    @Override
    public List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException {
        List<UserHotelDto> userHotelDtoList = new ArrayList<>();
        try {
            SearchOrder searchOrder = toOrder(orderDto);
            List<Hotel> hotelList = hotelDao.getListByOrder(searchOrder, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDtoList;
    }

    @Override
    public UserHotelDto getByOrder(OrderDto orderDto) throws ServiceException {
        UserHotelDto userHotelDto = null;
        try {
            Long hotelId = orderDto.getHotel().getHotelId();
            Hotel hotel = hotelDao.getById(hotelId);
            userHotelDto = converter.toDto(hotel);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDto;
    }

    public Long getHotelsCount(OrderDto orderDto) throws ServiceException {
        Long hotelsCount = null;
        try {
            SearchOrder searchOrder = toOrder(orderDto);
            hotelsCount = hotelDao.getHotelsCount(searchOrder);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return hotelsCount;
    }

    private SearchOrder toOrder(OrderDto orderDto) throws DaoException, LocalisationException {
        UserDto userDto = orderDto.getUser();
        User user = null;
        if (userDto != null) {
            user = userConverter.toEntity(userDto);
        }
        RoomTypeDto roomTypeDto = orderDto.getRoomType();
        RoomType roomType = (roomTypeDto != null) ? roomTypeConverter.safeToEntity(roomTypeDto) : null;
        HotelDto hotelDto = orderDto.getHotel();
        Hotel hotel = hotelConverter.toEntity(hotelDto);
        Integer totalPersons = orderDto.getTotalPersons();
        Integer totalRooms = orderDto.getTotalRooms();
        Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
        Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
        String sortPrice = orderDto.getSortPrice();
        Boolean sortPriceAsc = null;
        if (StringUtils.isNotBlank(sortPrice)) {
            sortPriceAsc = !sortPrice.equals(FieldValue.SORT_DESCENDING);
        } else {
            sortPriceAsc = true;
        }
        return new SearchOrder(user, hotel, roomType, totalPersons, totalRooms, checkInDate, checkOutDate, sortPriceAsc);
    }

}
