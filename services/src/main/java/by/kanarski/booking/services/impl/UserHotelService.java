package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.HotelTranslationDao;
import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class UserHotelService extends ExtendedBaseService<Hotel, UserHotelDto> implements IUserHotelService {

    private static UserHotelService instance;
    private static HotelDao hotelDao = HotelDao.getInstance();
    private static RoomDao roomDao = RoomDao.getInstance();

    private UserHotelService() {
    }

    public static synchronized UserHotelService getInstance() {
        if (instance == null) {
            instance = new UserHotelService();
        }
        return instance;
    }

    @Override
    public UserHotelDto getByHotelName(String hotelName) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserHotelDto userHotelDto = null;
        SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("hotelName", hotelName);
        hotelFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getUniqueByFilter(hotelFilter);
            Hotel hotel = hotelTranslation.getHotel();
            userHotelDto = converter.toDto(hotel);
            transaction.rollback();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDto;
    }

    @Override
    public List<UserHotelDto> getByCountry(String country, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<UserHotelDto> userHotelDtoList = null;
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
        locationFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            country = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCountry();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.country", country);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDtoList;
    }

    @Override
    public List<UserHotelDto> getByCity(String city, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<UserHotelDto> userHotelDtoList = null;
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", city);
        locationFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            city = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCity();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.city", city);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDtoList;
    }

    @Override
    public List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<UserHotelDto> userHotelDtoList =  new ArrayList<>();
        HotelDto hotelDto = orderDto.getHotel();
        Long hotelId = hotelDto.getHotelId();
        String country = hotelDto.getLocation().getCountry();
        String city = hotelDto.getLocation().getCity();
        SearchFilter hotelFilter = new SearchFilter();
        try {
            transaction.begin();
            if (hotelId != null) {
                hotelFilter.setEqFilter("hotelId", hotelId);
            } else {
                if (StringUtils.isNotBlank(country)) {
                    SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
                    if (StringUtils.isNotBlank(city)) {
                        locationFilter.setEqFilter("city", city);
                    }
                    locationFilter.setEqFilter("language", "EN");
                    List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
                            .getListByFilter(hotelFilter);
                    if (locationTranslationList.size() > 0) {
                        LocationTranslation locationTranslation = locationTranslationList.get(0);
                        if (locationTranslationList.size() == 1) {
                            Long locationId = locationTranslation.getLocation().getLocationId();
                            hotelFilter.setEqFilter("location.locationId", locationId);
                        } else {
                            country = locationTranslation.getCountry();
                            hotelFilter.setEqFilter("location.country", country);
                        }
                    }
                }
            }
//            Integer totalPersons = orderDto.getTotalPersons();
//            hotelFilter.setGeFilter("roomType.maxPersons", totalPersons);
//            Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
//            Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDtoList;
    }

    @Override
    public UserHotelDto getByOrder(OrderDto orderDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserHotelDto userHotelDto = null;
        try {
            transaction.begin();
            Hotel hotel = HotelDao.getInstance().getById(orderDto.getHotel().getHotelId());
            userHotelDto = converter.toDto(hotel);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDto;
    }



}
