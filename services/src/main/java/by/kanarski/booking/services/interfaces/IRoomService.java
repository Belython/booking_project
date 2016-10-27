package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public interface IRoomService extends IService<RoomDto> {

    List<RoomDto> getAvailableRooms(OrderDto orderDto) throws ServiceException;

    List<RoomDto> getByHotelId(long hotelId) throws ServiceException;

    List<RoomDto> getByBill(BillDto billDto) throws ServiceException;

    void updateList(List<RoomDto> roomDtoList) throws ServiceException;

    void addList(List<RoomDto> roomDtoList) throws ServiceException;

    void reserveRoomList(List<RoomDto> roomDtoList) throws ServiceException;

    List<RoomDto> getByIdList(List<Long> roomIdList) throws ServiceException;

}
