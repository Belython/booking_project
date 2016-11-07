package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Room dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IRoomDao extends IDao<Room> {

    List<Room> getAvailableRooms(OrderDto orderDto) throws DaoException;

    List<Room> getByHotelId(long hotelId) throws DaoException;

    List<Room> getByIdList(List<Long> idList) throws DaoException;

    void updateList(List<Room> roomList) throws DaoException;

    void addList(List<Room> roomList) throws DaoException;

    void reserveRoomList(List<Room> roomList) throws DaoException;

}
