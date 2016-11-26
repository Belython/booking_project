package by.kanarski.booking.managers;

import java.util.ResourceBundle;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum ExceptionMessageManager {

    //Dao messages

    ADD_EXCEPTION("exceptionMessage.addException"),
    GET_EXCEPTION("exceptionMessage.getException"),
    UPDATE_EXCEPTION("exceptionMessage.updateException"),
    DELETE_EXCEPTION("exceptionMessage.deleteException"),
    WRONG_DATASOURCE_SETTINGS("exceptionMessage.wrongDatasourceSettings"),
    INPUT_ERROR("exceptionMessage.inputError"),
    DATABASE_CONNECTION_ERROR("exceptionMessage.databaseConnectionError"),

    //Service messages

    TRANSACTION_FAILED("exceptionMessage.transactionFailed"),
    ROLLBACK_FAILED("exceptionMessage.rollbackFailed"),

    //L10n messages

    PARSE_DATE_EXCEPTION("exceptionMessage.parseDateException");

    private String resourceKey;

    ExceptionMessageManager(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String get() {
        ResourceBundle bundle = ResourceManager.EXCEPTION_MESSAGES.get();
        String property = bundle.getString(resourceKey);
        return property;
    }

}
