package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;
import by.kanarski.booking.utils.SerializationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
