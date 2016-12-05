package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;


public interface IUserHotelService extends IExtendedBaseService<Hotel, UserHotelDto> {

//    List<UserHotelDto> getByHotelName(String hotelName) throws ServiceException;
//
//    List<UserHotelDto> getByCountry(String country, int page, int perPage) throws ServiceException;
//
//    List<UserHotelDto> getByCity(String city, int page, int perPage) throws ServiceException;

    List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException;

    UserHotelDto getByOrder(OrderDto orderDto) throws ServiceException;

}