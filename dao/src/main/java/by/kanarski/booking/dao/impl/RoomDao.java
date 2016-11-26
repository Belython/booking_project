package by.kanarski.booking.dao.impl;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.dao.interfaces.IRoomDao;

public class RoomDao extends ExtendedBaseDao<Room> implements IRoomDao {

    private static RoomDao instance = null;

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        if (instance == null) {
            instance = new RoomDao();
        }
        return instance;
    }

}
