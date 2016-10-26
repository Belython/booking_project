package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Location dao iterface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface ILocationDao extends IDao<Location> {

    void updateList(List<Location> locationList) throws DaoException;

    void addList(List<Location> locationList) throws DaoException;

}
