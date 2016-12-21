package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.exceptions.RegistrationException;

/**
 * User service service interface
 * @author Dzmitry Kanarski
 * @version 1.0
 */
public interface IUserService extends IExtendedBaseService<User, UserDto> {

    /**
     * Recives user DTO with status active by login
     * @param login user login
     * @return required user DTO
     * @throws ServiceException
     */
    UserDto getByLogin(String login) throws ServiceException;

    /**
     * Recives user DTO with status active by email
     * @param email user email
     * @return required user DTO
     * @throws ServiceException
     */
    UserDto getByEmail(String email) throws ServiceException;

    /**
     * Checks whether a user with given credentials in the database
     * @param userDto user DTO, that contains required credentials
     * @return true, if with same credentials exist, else false
     * @throws ServiceException
     */
    boolean isNewUser(UserDto userDto) throws ServiceException;

    /**
     * Adds user into database
     * @param userDto an user to add
     * @throws ServiceException
     */
    void registerUser(UserDto userDto) throws ServiceException, RegistrationException;

}
