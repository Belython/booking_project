package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Hotel dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */

public interface IHotelDao extends IBaseDao<Hotel> {

    void updateList(List<Hotel> hotelList) throws DaoException;

    /**
     *
     * @param hotelName
     * @return
     * @throws DaoException
     */
    Hotel getByHotelName(String hotelName, Location location) throws DaoException;

    List<Hotel> getByLocation(Location location) throws DaoException;

    void addList(List<Hotel> hotelList) throws DaoException;

}
