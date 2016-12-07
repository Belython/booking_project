package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;

public interface IUserService extends IExtendedBaseService<User, UserDto> {

    boolean isAuthorized(UserDto userDto) throws ServiceException;

    UserDto getByLogin(String login) throws ServiceException;

    UserDto getByEmail(String email) throws ServiceException;

    boolean isNewUser(UserDto userDto) throws ServiceException;

    void registerUser(UserDto userDto) throws ServiceException;

    UserDto loginUser(UserDto unauthorizedUser) throws ServiceException;


    }
