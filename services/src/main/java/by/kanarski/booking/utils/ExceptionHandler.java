package by.kanarski.booking.utils;

import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;

import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {

    public static void handleSQLOrDaoException(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
            throw new ServiceException(ExceptionMessageManager.TRANSACTION_FAILED.get(), exception);
        } catch (SQLException e) {
            throw new ServiceException(ExceptionMessageManager.ROLLBACK_FAILED.get(), exception);
        }
    }

    public static void handleDaoException(TransactoinWrapper transaction, Exception exception) throws ServiceException {
        transaction.rollback();
        throw new ServiceException(ExceptionMessageManager.TRANSACTION_FAILED.get(), exception);
    }



}
