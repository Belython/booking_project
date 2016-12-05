package by.kanarski.booking.utils.transaction;

import by.kanarski.booking.utils.SystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SimpleTransaction implements TransactoinWrapper {

    private Connection connection;
    private SystemLogger logger = SystemLogger.getInstance();

    public SimpleTransaction() {
        this.connection = ConnectionUtil.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.logError(getClass(), "msg", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.logError(getClass(), "msg", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.logError(getClass(), "msg", e);
        }
    }

    @Override
    public void clean() {
        ConnectionUtil.closeConnection();
    }
}
