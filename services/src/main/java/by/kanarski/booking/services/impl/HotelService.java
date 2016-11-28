package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.HotelTranslationDao;
import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
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
import org.apache.commons.lang3.StringUtils;

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
        SearchFilter hotelFilter = SearchFilter.createLanguageFilter("EN");
        hotelFilter.setEqFilter(SearchParameter.HOTELNAME, hotelName);
        try {
            transaction.begin();
            HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getListByFilter(hotelFilter, 0, 1).get(0);
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
        locationFilter.setEqFilter(SearchParameter.LANGUAGE, "EN");
        try {
            transaction.begin();
            country = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCountry();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.LOCATION_COUNTRY, country);
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
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter(SearchParameter.CITY, city);
        locationFilter.setEqFilter(SearchParameter.LANGUAGE, "EN");
        try {
            transaction.begin();
            city = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCity();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.LOCATION_CITY, city);
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

    public List<HotelDto> getByDestination(DestinationDto destinationDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<HotelDto> hotelDtoList = null;
        SearchFilter hotelFilter = SearchFilter.createLanguageFilter("EN");
        try {
            transaction.begin();

            country = LocationTranslationDao.getInstance().getListByFilter(hotelFilter, 0, 1).get(0).getCountry();
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.LOCATION_COUNTRY, country);
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

    private String getDefaultHotelName (String hotelName) {
        SearchFilter hotelFilter = SearchFilter.createLanguageFilter("EN");
        hotelFilter.setEqFilter(SearchParameter.HOTELNAME, hotelName);
        HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getListByFilter(hotelFilter, 0, 1).get(0);
    }
}
