package by.kanarski.booking.utils;

import by.kanarski.booking.managers.DatabaseManager;
import by.kanarski.booking.managers.ExceptionMessageManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents connections pool based on c3p0 library
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see ComboPooledDataSource
 */

public class DataSource {
    private static DataSource datasource = null;
    private ComboPooledDataSource cpds = null;

    /**
     * Connections pool cashe with configuration
     */

    private DataSource() {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(DatabaseManager.JDBC_DRIVER_PATH.get());
            cpds.setJdbcUrl(DatabaseManager.DATABASE_PATH.get());
            cpds.setUser(DatabaseManager.USER_NAME.get());
            cpds.setPassword(DatabaseManager.PASSWORD.get());
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(50);
            cpds.setMaxStatements(180);
            cpds.setInitialPoolSize(10);
            //Avoding memmory leaks on hot redeploy
            cpds.setContextClassLoaderSource("library");
            cpds.setPrivilegeSpawnedThreads(true);
        } catch (PropertyVetoException e) {
            BookingSystemLogger.getInstance().logError(getClass(),
                    ExceptionMessageManager.WRONG_DATASOURCE_SETTINGS.get() + e);
        }
    }

    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            datasource = new DataSource();
        }
        return datasource;
    }

    /**
     * Gives one of connectons from pool
     * @return connection
     * @throws SQLException
     */

    public Connection getConnection() throws SQLException {
        Connection connection = cpds.getConnection();
        return connection;
    }

    public void destroy() {
        cpds.close();
    }

}
