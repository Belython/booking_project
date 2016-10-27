package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRoomTypeService extends IService<RoomTypeDto> {

    void updateList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException;

    void addList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException;

}
