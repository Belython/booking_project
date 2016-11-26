package by.kanarski.booking.dao.impl;

import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.dao.interfaces.ILocationDao;

public class LocationDao extends ExtendedBaseDao<Location> implements ILocationDao {

    private static LocationDao instance = null;

    private LocationDao() {
    }

    public static synchronized LocationDao getInstance() {
        if (instance == null) {
            instance = new LocationDao();
        }
        return instance;
    }

}
