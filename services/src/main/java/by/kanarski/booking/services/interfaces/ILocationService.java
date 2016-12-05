package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface ILocationService extends IExtendedBaseService<Location, LocationDto> {

    void updateList(List<LocationDto> locationDtoList) throws ServiceException;

    void addList(List<LocationDto> locationDtoList) throws ServiceException;

}
