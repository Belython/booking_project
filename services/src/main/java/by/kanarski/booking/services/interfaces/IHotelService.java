package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Дмитрий on 21.07.2016.
 */
public interface IHotelService extends IBaseService<HotelDto> {

    HotelDto getByHotelName(String hotelName) throws ServiceException;

    List<HotelDto> getByCountry(String country, int page, int perPage) throws ServiceException;

    List<HotelDto> getByCity(String city, int page, int perPage) throws ServiceException;

}
