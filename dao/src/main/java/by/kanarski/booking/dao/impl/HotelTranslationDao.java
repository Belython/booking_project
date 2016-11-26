package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IHotelTranslationDao;
import by.kanarski.booking.entities.hotel.HotelTranslation;

public class HotelTranslationDao extends ExtendedBaseDao<HotelTranslation> implements IHotelTranslationDao {

    private static HotelTranslationDao instance = null;

    private HotelTranslationDao() {
    }

    public static synchronized HotelTranslationDao getInstance() {
        if (instance == null) {
            instance = new HotelTranslationDao();
        }
        return instance;
    }

}
