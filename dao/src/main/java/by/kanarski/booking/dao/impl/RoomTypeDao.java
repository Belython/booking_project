package by.kanarski.booking.dao.impl;

import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.dao.interfaces.IRoomTypeDao;

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
