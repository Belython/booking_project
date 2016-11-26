package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IRoomService extends IBaseService<RoomDto> {

    List<RoomDto> getAvailableRooms(OrderDto orderDto, int page, int perPage) throws ServiceException;

    List<RoomDto> getByHotelId(Long hotelIdint, int page, int perPage) throws ServiceException;

//    List<RoomDto> getByBill(BillDto billDto, int page, int perPage) throws ServiceException;

}
