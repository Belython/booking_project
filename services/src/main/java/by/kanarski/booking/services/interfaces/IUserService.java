package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUserService extends IService<UserDto> {

    boolean checkAuthorization(UserDto userDto) throws ServiceException;

    UserDto getByLogin(String login) throws ServiceException;

    UserDto getByEmail(String email) throws ServiceException;

    boolean checkIsNewUser(UserDto userDto) throws ServiceException;

    void registrateUser(UserDto userDto) throws ServiceException;

}
