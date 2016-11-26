package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;

public interface IUserService extends IBaseService<UserDto> {

    boolean isAuthorized(UserDto userDto) throws ServiceException;

    UserDto getByLogin(String login) throws ServiceException;

    UserDto getByEmail(String email) throws ServiceException;

    boolean isNewUser(UserDto userDto) throws ServiceException;

    void registrateUser(UserDto userDto) throws ServiceException;

}
