package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hotel dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IHotelDao extends IDao<Hotel> {

    void updateList(List<Hotel> hotelList) throws DaoException;

    Hotel getByHotelName(String hotelName) throws DaoException;

    List<Hotel> getByCountry(String country) throws DaoException;

    List<Hotel> getByCity(String city) throws DaoException;

    void addList(List<Hotel> hotelList) throws DaoException;

}
