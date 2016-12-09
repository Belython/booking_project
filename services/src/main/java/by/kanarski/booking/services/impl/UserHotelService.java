package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dto.Order;
import by.kanarski.booking.dto.OrderDto;
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
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
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

//    @Override
//    public UserHotelDto getByHotelName(String hotelName) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        UserHotelDto userHotelDto = null;
//        SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("hotelName", hotelName);
//        hotelFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getUniqueByFilter(hotelFilter);
//            Hotel hotel = hotelTranslation.getHotel();
//            userHotelDto = converter.toDto(hotel);
//            transaction.rollback();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDto;
//    }
//
//    @Override
//    public List<UserHotelDto> getByCountry(String country, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<UserHotelDto> userHotelDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            country = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCountry();
//            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.country", country);
//            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
//            userHotelDtoList = converter.toDtoList(hotelList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDtoList;
//    }
//
//    @Override
//    public List<UserHotelDto> getByCity(String city, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<UserHotelDto> userHotelDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", city);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            city = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCity();
//            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.city", city);
//            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
//            userHotelDtoList = converter.toDtoList(hotelList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDtoList;
//    }

    @Override
    public List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException {
        List<UserHotelDto> userHotelDtoList =  new ArrayList<>();
        try {
            Order order = toOrder(orderDto);
            List<Hotel> hotelList = hotelDao.getListByOrder(order, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
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
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDto;
    }

    public Long getHotelsCount(OrderDto orderDto) throws ServiceException {
        Long hotelsCount = null;
        try {
            Order order = toOrder(orderDto);
            hotelsCount = hotelDao.getHotelsCount(order);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelsCount;
    }

    private Order toOrder(OrderDto orderDto) throws DaoException, LocalisationException {
        String language = orderDto.getLanguage();
        DtoToEntityConverter<Hotel, HotelDto> hotelConverter =
                new DtoToEntityConverter<>(Hotel.class, HotelDto.class, language);
        DtoToEntityConverter<User, UserDto> userConverter =
                new DtoToEntityConverter<>(User.class, UserDto.class, language);
        DtoToEntityConverter<RoomType, RoomTypeDto> roomTypeConverter =
                new DtoToEntityConverter<>(RoomType.class, RoomTypeDto.class, language);
        UserDto userDto = orderDto.getUser();
        User user = null;
        if (userDto != null) {
            user = userConverter.toEntity(userDto);
        }
        RoomTypeDto roomTypeDto = orderDto.getRoomType();
        RoomType roomType = (roomTypeDto != null) ? roomTypeConverter.toEntity(roomTypeDto) : null;
        HotelDto hotelDto = orderDto.getHotel();
        Hotel hotel = hotelConverter.toEntity(hotelDto);
        Integer totalPersons = orderDto.getTotalPersons();
        Integer totalRooms = orderDto.getTotalRooms();
        Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
        Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
        return new Order(user, hotel, roomType, totalPersons, totalRooms, checkInDate, checkOutDate);
    }


}
