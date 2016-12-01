package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Дмитрий on 21.07.2016.
 */
public interface IHotelService extends IBaseService<HotelDto> {

    List<HotelDto> getByHotelName(HotelDto hotelDto, int page, int perPage) throws ServiceException;

    List<HotelDto> getByCountry(HotelDto hotelDto, int page, int perPage) throws ServiceException;

    List<HotelDto> getByCity(HotelDto hotelDto, int page, int perPage) throws ServiceException;

}
