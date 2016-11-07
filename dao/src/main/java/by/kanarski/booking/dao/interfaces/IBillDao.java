package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.DaoException;

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
