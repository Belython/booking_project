package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.wrappers.SearchFilter;

import java.util.List;

public class HotelDao extends ExtendedBaseDao<Hotel> implements IHotelDao {

    private static HotelDao instance = null;

    public static synchronized HotelDao getInstance() {
        if (instance == null) {
            instance = new HotelDao();
        }
        return instance;
    }

    @Override
    public Hotel getByHotelName(String hotelName, Location location) throws DaoException {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setEqFilter("hotelName", hotelName);
        searchFilter.setEqFilter("locationId", location.getLocationId());
        return getUniqueByFilter(searchFilter);
    }

    @Override
    public List<Hotel> getByLocation(Location location) throws DaoException {
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter("locationId", location.getLocationId());
        return getListByFilter(searchFilter);
    }
}
