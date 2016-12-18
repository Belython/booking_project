package by.kanarski.booking.utils;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IFacilityTranslationDao;
import by.kanarski.booking.dao.interfaces.IHotelTranslationDao;
import by.kanarski.booking.dao.interfaces.ILocationTranslationDao;
import by.kanarski.booking.dao.interfaces.IRoomTypeTranslationDao;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
public class ServiceHelper {

    @Autowired
    private IHotelTranslationDao hotelTranslationDao;

    @Autowired
    private ILocationTranslationDao locationTranslationDao;

    @Autowired
    private IRoomTypeTranslationDao roomTypeTranslationDao;

    @Autowired
    private IFacilityTranslationDao facilityTranslationDao;

    public String getNotLocalizedHotelName(String hotelName, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        if (hotelName.contains(" ")) {
            filter.addEqFilter(SearchParameter.HOTELNAME, hotelName);
        } else {
            filter.addILikeFilter(SearchParameter.HOTELNAME, hotelName);
        }
        List<HotelTranslation> hotelTranslationList = hotelTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedHotelName = null;
        if (hotelTranslationList.size() > 0) {
            notLocalizedHotelName = hotelTranslationList.get(0).getHotel().getHotelName();
        }
        return notLocalizedHotelName;
    }

    public String getNotLocalizedCountry(String country, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        if (country.contains(" ")) {
            filter.addEqFilter(SearchParameter.COUNTRY, country);
        } else {
            filter.addILikeFilter(SearchParameter.COUNTRY, country);
        }
        List<LocationTranslation> locationTranslationList = locationTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedCountry = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCountry = locationTranslationList.get(0).getLocation().getCountry();
        }
        return notLocalizedCountry;
    }

    public String getNotLocalizedCity(String city, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        if (city.contains(" ")) {
            filter.addEqFilter(SearchParameter.CITY, city);
        } else {
            filter.addILikeFilter(SearchParameter.CITY, city);
        }
        List<LocationTranslation> locationTranslationList = locationTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedCity = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCity = locationTranslationList.get(0).getLocation().getCity();
        }
        return notLocalizedCity;
    }

    public String getNotLocalizedRoomTypeName(String roomTypeName, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        if (roomTypeName.contains(" ")) {
            filter.addEqFilter(SearchParameter.ROOMTYPENAME, roomTypeName);
        } else {
            filter.addILikeFilter(SearchParameter.ROOMTYPENAME, roomTypeName);
        }
        List<RoomTypeTranslation> roomTypeTranslationList = roomTypeTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedRoomTypeName = null;
        if (roomTypeTranslationList.size() > 0) {
            notLocalizedRoomTypeName = roomTypeTranslationList.get(0).getRoomType().getRoomTypeName();
        }
        return notLocalizedRoomTypeName;
    }

    public String getNotLocalizedFacilityName(String facilityName, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        if (facilityName.contains(" ")) {
            filter.addEqFilter(SearchParameter.FACILITYNAME, facilityName);
        } else {
            filter.addILikeFilter(SearchParameter.FACILITYNAME, facilityName);
        }
        List<FacilityTranslation> facilityTranslationList = facilityTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizerFacilityName = null;
        if (facilityTranslationList.size() > 0) {
            notLocalizerFacilityName = facilityTranslationList.get(0).getFacility().getFacilityName();
        }
        return notLocalizerFacilityName;
    }

}
