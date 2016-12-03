package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.filter.SearchFilter;

public class UserService extends ExtendedBaseService<User, UserDto> implements IUserService {

    private static UserService instance;
    private static UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserDto userDto = null;
        try {
            transaction.begin();
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter("login", login);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = converter.toDto(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userDto;
    }

    @Override
    public UserDto getByEmail(String email) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserDto userDto = null;
        try {
            transaction.begin();
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter("login", email);
            User user = userDao.getUniqueByFilter(searchFilter);
            userDto = converter.toDto(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userDto;
    }

    @Override
    public boolean isAuthorized(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        boolean isAuthorized = false;
        try {
            transaction.begin();
            SearchFilter searchFilter = new SearchFilter();
            searchFilter.addEqFilter("login", userDto.getLogin());
            searchFilter.addEqFilter("password", userDto.getPassword());
            User user = userDao.getUniqueByFilter(searchFilter);
            isAuthorized = !(user == null);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return isAuthorized;
    }

    @Override
    public boolean isNewUser(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        boolean isNewUser = false;
        try {
            transaction.begin();
            SearchFilter searchFilter = new SearchFilter();
            searchFilter.addEqFilter("login", userDto.getLogin());
            User user = userDao.getUniqueByFilter(searchFilter);
            isNewUser = (user == null);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return isNewUser;
    }

    @Override
    public void registrateUser(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            User user = converter.toEntity(userDto);
            userDao.add(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
    }

}
