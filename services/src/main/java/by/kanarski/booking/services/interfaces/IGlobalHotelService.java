package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface IGlobalHotelService extends IService<GlobalHotelDto> {

    GlobalHotelDto getByHotelName(String hotelName) throws ServiceException;

    void updateList(List<GlobalHotelDto> globalHotelDtoList) throws ServiceException;

    void addList(List<GlobalHotelDto> globalHotelDtoList) throws ServiceException;

    List<GlobalHotelDto> getByCountry(String country) throws ServiceException;

    List<GlobalHotelDto> getByCity(String city) throws ServiceException;

    List<GlobalHotelDto> getByOrder(OrderDto orderDto) throws ServiceException;

    GlobalHotelDto getByOrder1(OrderDto orderDto) throws ServiceException;

}
