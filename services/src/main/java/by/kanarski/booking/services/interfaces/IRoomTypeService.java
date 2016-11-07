package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IRoomTypeService extends IService<RoomTypeDto> {

    void updateList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException;

    void addList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException;

}
