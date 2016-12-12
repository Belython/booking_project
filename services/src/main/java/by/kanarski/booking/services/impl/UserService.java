package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserService extends ExtendedBaseService<User, UserDto> implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        UserDto userDto = null;
        try {
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LOGIN, login);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = converter.toDto(user);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return userDto;
    }

    @Override
    public UserDto getByEmail(String email) throws ServiceException {
        UserDto userDto = null;
        try {
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.EMAIL, email);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = converter.toDto(user);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return userDto;
    }

    @Override
    public boolean isAuthorized(UserDto userDto) throws ServiceException {
        boolean isAuthorized = false;
        try {
            SearchFilter searchFilter = new SearchFilter();
            searchFilter.addEqFilter(SearchParameter.LOGIN, userDto.getLogin());
            searchFilter.addEqFilter(SearchParameter.PASSWORD, userDto.getPassword());
            User user = userDao.getUniqueByFilter(searchFilter);
            isAuthorized = !(user == null);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return isAuthorized;
    }

    @Override
    public boolean isNewUser(UserDto userDto) throws ServiceException {
        boolean isNewUser = false;
        try {
            SearchFilter searchFilter = new SearchFilter();
            searchFilter.addEqFilter(SearchParameter.LOGIN, userDto.getLogin());
            User user = userDao.getUniqueByFilter(searchFilter);
            isNewUser = (user == null);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return isNewUser;
    }

    @Override
    public void registerUser(UserDto userDto) throws ServiceException {
        try {
            User user = converter.toEntity(userDto);
            userDao.add(user);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    public UserDto loginUser(UserDto unauthorizedUser) throws ServiceException {
        UserDto authorizedUser = null;
        SearchFilter searchFilter = SearchFilter
                .createBasicEqFilter(SearchParameter.LOGIN, unauthorizedUser.getLogin())
                .addEqFilter(SearchParameter.PASSWORD, unauthorizedUser.getPassword());
        try {
            User user = userDao.getUniqueByFilter(searchFilter);
            authorizedUser = (user != null) ? converter.toDto(user) : null;
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return authorizedUser;
    }

}
