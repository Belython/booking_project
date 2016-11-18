package by.kanarski.booking.newDao.interfaces;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Hotel dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IHotelDao extends IDao<Hotel> {

    void updateList(List<Hotel> hotelList) throws DaoException;

    /**
     *
     * @param hotelName
     * @return
     * @throws DaoException
     */
    Hotel getByHotelName(String hotelName) throws DaoException;

    List<Hotel> getByCountry(String country) throws DaoException;

    List<Hotel> getByCity(String city) throws DaoException;

    void addList(List<Hotel> hotelList) throws DaoException;

}
