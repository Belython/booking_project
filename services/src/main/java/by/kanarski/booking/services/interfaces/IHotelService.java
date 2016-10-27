package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дмитрий on 21.07.2016.
 */
public interface IHotelService extends IService<HotelDto> {

    HotelDto getByHotelName(String hotelName) throws ServiceException;

    void updateList(List<HotelDto> hotelDtoList) throws ServiceException;

    void addList(List<HotelDto> hotelDtoList) throws ServiceException;

    List<HotelDto> getByCountry(String country) throws ServiceException;

    List<HotelDto> getByCity(String city) throws ServiceException;

}
