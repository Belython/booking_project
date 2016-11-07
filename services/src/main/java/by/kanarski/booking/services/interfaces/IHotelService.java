package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;

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
