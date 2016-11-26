package by.kanarski.booking.dao.impl;

import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.dao.interfaces.IRoomTypeTranslationDao;

public class RoomTypeTranslationDao extends ExtendedBaseDao<RoomTypeTranslation> implements IRoomTypeTranslationDao {

    private static RoomTypeTranslationDao instance = null;

    private RoomTypeTranslationDao() {
    }

    public static synchronized RoomTypeTranslationDao getInstance() {
        if (instance == null) {
            instance = new RoomTypeTranslationDao();
        }
        return instance;
    }

}
