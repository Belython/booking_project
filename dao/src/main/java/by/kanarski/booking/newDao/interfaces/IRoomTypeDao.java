package by.kanarski.booking.newDao.interfaces;

import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Room type dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IRoomTypeDao extends IDao<RoomType> {

    void updateList(List<RoomType> roomTypeList) throws DaoException;

    void addList(List<RoomType> roomTypeList) throws DaoException;

}
