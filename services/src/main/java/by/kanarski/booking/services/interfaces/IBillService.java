package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
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

public interface IBillService extends IService<BillDto> {

    List<BillDto> getByUserId(long userId) throws ServiceException;

}
