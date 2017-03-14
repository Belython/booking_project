package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.MessageKey;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.constants.StateValue;
import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.RegistrationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserService extends ExtendedBaseService<User, UserDto> implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        UserDto userDto = null;
        try {
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LOGIN, login);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = conversionService.convert(user, UserDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return userDto;
    }

    @Override
    public UserDto getByEmail(String email) throws ServiceException {
        UserDto userDto = null;
        try {
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.EMAIL, email);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = conversionService.convert(user, UserDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return userDto;
    }

    @Override
    public boolean isNewUser(UserDto userDto) throws ServiceException {
        return (getByLogin(userDto.getUserName()) == null);
    }

    @Override
    public void registerUser(UserDto userDto) throws ServiceException, RegistrationException {
        if (!isNewUser(userDto)) {
            String messageKey = MessageKey.USER_EXISTS;
            throw new RegistrationException(messageKey);
        }
        String rawPassword = userDto.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);
        userDto.setPassword(hashPassword);
        userDto.getRoleSet().add(StateValue.ROLE_USER);
        userDto.setUserStatus(StateValue.STATUS_ACTIVE);
//        try {
//            User user = conversionService.convert(userDto, User.class);
//            userDao.add(user);
            add(userDto);
//        } catch (DaoException e) {
//            BookingExceptionHandler.handleDaoException(e);
//        }
    }

    @Override
    protected IExtendedBaseDao<User> getDao() {
        return userDao;
    }
}
