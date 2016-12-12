package by.kanarski.booking.utils;

import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ExceptionMessageManager;

import java.sql.Connection;
import java.sql.SQLException;

public class BookingExceptionHandler {

    private static final SystemLogger LOGGER = SystemLogger.getInstance().setSender(BookingExceptionHandler.class);

    public static void handleSQLOrDaoException(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
            throw new ServiceException(ExceptionMessageManager.TRANSACTION_FAILED.get(), exception);
        } catch (SQLException e) {
            throw new ServiceException(ExceptionMessageManager.ROLLBACK_FAILED.get(), exception);
        }
    }

    public static void handleDaoException(Exception exception) throws ServiceException {
        throw new ServiceException(exception.getMessage(), exception);
    }

    public static void handleLocalizationException(Exception exception) {
        LOGGER.logError(exception.getMessage(), exception);
    }

    public static void handleUsernameNotFoundException(Exception exception) {
        LOGGER.logError(exception.getMessage(), exception);
    }

    public static void handleServiceException(Exception exception) {
        LOGGER.logError(exception.getMessage(), exception);

    }



}
