package by.kanarski.booking.utils;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IHotelTranslationDao;
import by.kanarski.booking.dao.interfaces.ILocationTranslationDao;
import by.kanarski.booking.dao.interfaces.IRoomTypeTranslationDao;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.LocationTranslation;
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

    public String getNotLocalizedHotelName(String hotelName, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        filter.addILikeFilter(SearchParameter.HOTELNAME, hotelName);
        List<HotelTranslation> hotelTranslationList = hotelTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedHotelName = null;
        if (hotelTranslationList.size() > 0) {
            notLocalizedHotelName = hotelTranslationList.get(0).getHotel().getHotelName();
        }
        return notLocalizedHotelName;
    }

    public String getNotLocalizedCountry(String country, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        filter.addILikeFilter(SearchParameter.COUNTRY, country);
        List<LocationTranslation> locationTranslationList = locationTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedCountry = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCountry = locationTranslationList.get(0).getLocation().getCountry();
        }
        return notLocalizedCountry;
    }

    public String getNotLocalizedCity(String city, String language) throws DaoException {
        SearchFilter filter = SearchFilter.createLanguageFilter(language);
        filter.addILikeFilter(SearchParameter.CITY, city);
        List<LocationTranslation> locationTranslationList = locationTranslationDao.getListByFilter(filter, 0, 1);
        String notLocalizedCity = null;
        if (locationTranslationList.size() > 0) {
            notLocalizedCity = locationTranslationList.get(0).getLocation().getCity();
        }
        return notLocalizedCity;
    }

}
