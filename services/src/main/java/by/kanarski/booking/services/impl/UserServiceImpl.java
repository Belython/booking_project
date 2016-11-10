package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;

import java.util.List;

public class UserServiceImpl implements IUserService {
    
    private static UserServiceImpl instance;
    private UserDao userDao = UserDao.getInstance();

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

//    @Override
//    public void add(UserDto userDto) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        try {
//            User user = DtoToEntityConverter.toUser(userDto);
//            connection.setAutoCommit(false);
//            userDao.add(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//    }

//    @Override
//    public UserDto getById(long id) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        UserDto userDto = null;
//        try {
//            connection.setAutoCommit(false);
//            User user = userDao.getById(id);
//            userDto = DtoToEntityConverter.toUserDto(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return userDto;
//    }
//
//    @Override
//    public List<UserDto> getAll() throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        List<UserDto> userDtoList = null;
//        try {
//            connection.setAutoCommit(false);
//            List<User> userList = userDao.getAll();
//            userDtoList = DtoToEntityConverter.toUserDtoList(userList);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return userDtoList;
//    }
//
//
//
//    @Override
//    public void update(UserDto userDto) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        try {
//            connection.setAutoCommit(false);
//            User user = DtoToEntityConverter.toUser(userDto);
//            userDao.update(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//    }
//
//    @Override
//    public void delete(long id) throws ServiceException {
//
//    }
//
//    @Override
//    public boolean checkAuthorization(UserDto userDto) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        boolean isAuthorized = false;
//        try {
//            connection.setAutoCommit(false);
//            String login = userDto.getLogin();
//            String  password = userDto.getPassword();
//            isAuthorized = userDao.isAuthorized(login, password);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return isAuthorized;
//    }
//
//    @Override
//    public UserDto getByLogin(String login) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        UserDto userDto = null;
//        try {
//            connection.setAutoCommit(false);
//            User user = userDao.getByLogin(login);
//            userDto = DtoToEntityConverter.toUserDto(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return userDto;
//    }
//
//    @Override
//    public UserDto getByEmail(String email) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        UserDto userDto = null;
//        try {
//            connection.setAutoCommit(false);
//            User user = userDao.getByEmail(email);
//            userDto = DtoToEntityConverter.toUserDto(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return userDto;
//    }
//
//    @Override
//    public boolean checkIsNewUser(UserDto userDto) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        boolean isNew = false;
//        try {
//            connection.setAutoCommit(false);
//            String login = userDto.getLogin();
//            isNew = userDao.isNewUser(login);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return isNew;
//    }
//
//    @Override
//    public void registrateUser(UserDto userDto) throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        try {
//            connection.setAutoCommit(false);
//            User user = DtoToEntityConverter.toUser(userDto);
//            userDao.add(user);
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//    }

    @Override
    public void add(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            User user = DtoToEntityConverter.toUser(userDto);
            userDao.add(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
    }

    @Override
    public UserDto getById(long id) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserDto userDto = null;
        try {
            transaction.begin();
            User user = userDao.getById(id);
            userDto = DtoToEntityConverter.toUserDto(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAll() throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<UserDto> userDtoList = null;
        try {
            transaction.begin();
            List<User> userList = userDao.getAll();
            userDtoList = DtoToEntityConverter.toUserDtoList(userList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return userDtoList;
    }

    @Override
    public void update(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            User user = DtoToEntityConverter.toUser(userDto);
            userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    @Override
    public boolean checkAuthorization(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        boolean isAuthorized = false;
        try {
            transaction.begin();
            String login = userDto.getLogin();
            String  password = userDto.getPassword();
            isAuthorized = userDao.isAuthorized(login, password);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return isAuthorized;
    }

    @Override
    public UserDto getByLogin(String login) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserDto userDto = null;
        try {
            transaction.begin();
            User user = userDao.getByLogin(login);
            userDto = DtoToEntityConverter.toUserDto(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return userDto;
    }

    @Override
    public UserDto getByEmail(String email) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserDto userDto = null;
        try {
            transaction.begin();
            User user = userDao.getByEmail(email);
            userDto = DtoToEntityConverter.toUserDto(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return userDto;
    }

    @Override
    public boolean checkIsNewUser(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        boolean isNew = false;
        try {
            transaction.begin();
            String login = userDto.getLogin();
            isNew = userDao.isNewUser(login);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
        return isNew;
    }

    @Override
    public void registrateUser(UserDto userDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            transaction.begin();
            User user = DtoToEntityConverter.toUser(userDto);
            userDao.add(user);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
    }

    public void addList(List<UserDto> userDtoList) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            List<User> userList = DtoToEntityConverter.toUserList(userDtoList);
            userDao.addList(userList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
    }

    public void updateList(List<UserDto> userDtoList) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        try {
            List<User> userList = DtoToEntityConverter.toUserList(userDtoList);
            userDao.updateList(userList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        }
    }
}
