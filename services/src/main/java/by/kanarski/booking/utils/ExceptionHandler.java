package by.kanarski.booking.utils;

import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import org.hibernate.HibernateException;

import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {

    private static final BookingSystemLogger LOGGER = BookingSystemLogger.getInstance().setSender(ExceptionHandler.class);

    public static void handleSQLOrDaoException(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
            throw new ServiceException(ExceptionMessageManager.TRANSACTION_FAILED.get(), exception);
        } catch (SQLException e) {
            throw new ServiceException(ExceptionMessageManager.ROLLBACK_FAILED.get(), exception);
        }
    }

    public static void handleDaoException(TransactoinWrapper transaction, Exception exception) throws ServiceException {
        if (transaction != null) {
            try {
                transaction.rollback();
                throw new ServiceException(ExceptionMessageManager.TRANSACTION_FAILED.get(), exception);
            } catch (HibernateException e) {
                throw new ServiceException(ExceptionMessageManager.ROLLBACK_FAILED.get(), exception);
            }
        }
    }

    public static void handleDaoException(Exception exception) throws ServiceException {
        throw new ServiceException(exception.getMessage(), exception);
    }

    public static void handleLocalizationException(Exception exception) {
        LOGGER.logError(exception.getMessage(), exception);
    }



}
