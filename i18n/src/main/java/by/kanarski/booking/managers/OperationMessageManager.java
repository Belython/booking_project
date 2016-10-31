package by.kanarski.booking.managers;

import by.kanarski.booking.utils.threadLocal.UserPreferences;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum OperationMessageManager {

    WRONG_LOGIN_OR_PASSWORD("message.loginerror"),
    USER_EXISTS("message.userexsistserror"),
    EMPTY_LIST("message.emptylist"),
    EMPTY_CHOICE("message.emptychoice"),
    SUCCESS_OPERATION("message.successoperation"),
    FAILED_OPERATION("message.failedoperation"),
    NEGATIVE_ARGUMENT("message.negativeoperator"),
    INVALID_NUMBER_FORMAT("message.invalidnumberformat"),
    EMPTY_FIELDS("message.emptyfields"),
    ERROR_DATABASE("message.databaseerror"),
    AUTHORIZATION_ERRON("message.authorizationerror"),
    DATABASE_CHANGE_SUCCES("message.databaseChangeSucces"),
    PASSWORD_SENT("message.passwordSent"),
    LOW_ACCESS_LEVEL("message.lowAccessLevel");

    private String resourceKey;

    OperationMessageManager(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getLocalised() {
        Locale locale = UserPreferences.getLocale();
        ResourceBundle bundle = ResourceManager.OPERATION_MESSAGES.get(locale);
        String property = bundle.getString(resourceKey);
        return property;
    }

}
