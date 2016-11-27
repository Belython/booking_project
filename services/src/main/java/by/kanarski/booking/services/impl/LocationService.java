package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.LocationDao;
import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.ILocationService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.ArrayList;
import java.util.List;

public class LocationService extends ExtendedBaseService<Location, LocationDto> implements ILocationService {

    private static LocationService instance;
    private static LocationDao locationDao = LocationDao.getInstance();

    private LocationService() {
    }

    public static synchronized LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public List<LocationDto> getByCountry(String country, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<LocationDto> locationDtoList = null;
        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
        locationFilter.setEqFilter("language", "EN");
        try {
            transaction.begin();
            List<Location> locationList = new ArrayList<>();
            List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
                    .getListByFilter(locationFilter, page, perPage);
            for (LocationTranslation locationTranslation : locationTranslationList) {
                Location location = locationTranslation.getLocation();
                locationList.add(location);
            }
            locationDtoList = converter.toDtoList(locationList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return locationDtoList;
    }

//    public List<LocationDto> getByCountry(String country, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<LocationDto> locationDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
//        locationFilter.setEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            List<Location> locationList = new ArrayList<>();
//            List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
//                    .getListByFilter(locationFilter, page, perPage);
//            for (LocationTranslation locationTranslation : locationTranslationList) {
//                Location location = locationTranslation.getLocation();
//                locationList.add(location);
//            }
//            locationDtoList = converter.toDtoList(locationList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return locationDtoList;
//    }
}
