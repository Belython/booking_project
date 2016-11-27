package by.kanarski.booking.managers;

import java.util.ResourceBundle;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum DatabaseManager {

    DATABASE_PATH("database.dataBasePath"),
    TEST_DATABASE_PATH("database.testBasePath"),
    USER_NAME("database.userName"),
    PASSWORD("database.password"),
    JDBC_DRIVER_PATH("database.jdbcDriverPath");

    private String resourceKey;

    DatabaseManager(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String get() {
        ResourceBundle bundle = ResourceManager.DATABASE.get();
        String property = bundle.getString(resourceKey);
        return property;
    }

}
