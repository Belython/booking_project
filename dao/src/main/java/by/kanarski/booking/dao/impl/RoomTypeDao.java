package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IRoomTypeDao;
import by.kanarski.booking.entities.roomType.RoomType;

public class RoomTypeDao extends ExtendedBaseDao<RoomType> implements IRoomTypeDao {

    private static RoomTypeDao instance = null;

    private RoomTypeDao() {
    }

    public static synchronized RoomTypeDao getInstance() {
        if (instance == null) {
            instance = new RoomTypeDao();
        }
        return instance;
    }

}
