package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Bill dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IBillDao extends IDao<Bill> {

    List<Bill> getByUserId(long userId) throws DaoException;

}
