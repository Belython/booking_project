package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.exceptions.ServiceException;

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
