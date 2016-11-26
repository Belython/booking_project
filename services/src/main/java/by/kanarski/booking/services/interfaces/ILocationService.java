package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.LocationDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface ILocationService extends IBaseService<LocationDto> {

    void updateList(List<LocationDto> locationDtoList) throws ServiceException;

    void addList(List<LocationDto> locationDtoList) throws ServiceException;

}
