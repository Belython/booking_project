package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Location dao iterface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */

public interface ILocationDao extends IBaseDao<Location> {

    void updateList(List<Location> locationList) throws DaoException;

    void addList(List<Location> locationList) throws DaoException;

}
