package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Room type dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */

public interface IRoomTypeDao extends IBaseDao<RoomType> {

    void updateList(List<RoomType> roomTypeList) throws DaoException;

    void addList(List<RoomType> roomTypeList) throws DaoException;

}
