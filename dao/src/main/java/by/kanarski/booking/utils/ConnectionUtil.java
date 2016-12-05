package by.kanarski.booking.utils;

import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ConnectionUtil {
    private static DataSource dataSource = DataSource.getInstance();

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = (Connection) ThreadLocalUtil.CONNECTION.get();
            if (connection == null) {
                Connection newConnection = dataSource.getConnection();
                connection = (Connection) ThreadLocalUtil.CONNECTION.get(newConnection);
            }
        } catch (SQLException e) {
            SystemLogger.getInstance().logError(ConnectionUtil.class,
                    ExceptionMessageManager.INPUT_ERROR.get(), e);
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = getConnection();
        ClosingUtil.close(connection);
    }

}
