package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.HotelTranslationDao;
import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.List;


public class HotelService extends ExtendedBaseService<Hotel, HotelDto> implements IHotelService {

    private static HotelService instance;
    private static HotelDao hotelDao = HotelDao.getInstance();

    private HotelService() {
    }

    public static synchronized HotelService getInstance() {
        if (instance == null) {
            instance = new HotelService();
        }
        return instance;
    }

    @Override
    public HotelDto getByHotelName(String hotelName) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        HotelDto hotelDto = null;
        SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("hotelName", hotelName);
        hotelFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getUniqueByFilter(hotelFilter);
            Hotel hotel = hotelTranslation.getHotel();
            hotelDto = converter.toDto(hotel);
            transaction.rollback();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDto;
    }

    @Override
    public List<HotelDto> getByCountry(String country, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
        locationFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            country = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCountry();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.country", country);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByCity(String city, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", city);
        locationFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            city = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCity();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.city", city);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }
}
