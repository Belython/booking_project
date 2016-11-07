package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IRoomService extends IService<RoomDto> {

    List<RoomDto> getAvailableRooms(OrderDto orderDto) throws ServiceException;

    List<RoomDto> getByHotelId(long hotelId) throws ServiceException;

    List<RoomDto> getByBill(BillDto billDto) throws ServiceException;

    void updateList(List<RoomDto> roomDtoList) throws ServiceException;

    void addList(List<RoomDto> roomDtoList) throws ServiceException;

    void reserveRoomList(List<RoomDto> roomDtoList) throws ServiceException;

    List<RoomDto> getByIdList(List<Long> roomIdList) throws ServiceException;

}
