package by.kanarski.booking.managers;

import by.kanarski.booking.utils.threadLocal.UserPreferences;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum OperationMessageManager {

    WRONG_LOGIN_OR_PASSWORD("message.wrongLoginOrPassword"),
    USER_EXISTS("message.userExsistsError"),
    EMPTY_LIST("message.emptyList"),
    EMPTY_CHOICE("message.emptyChoice"),
    SUCCESS_OPERATION("message.successOperation"),
    ORDER_ACCEPTED("message.orderAccepted"),
    PAYMENT_RECIVED("message.paymentRecived"),
    FAILED_OPERATION("message.failedOperation"),
    NEGATIVE_ARGUMENT("message.negativeOperator"),
    INVALID_NUMBER_FORMAT("message.invalidNumberFormat"),
    EMPTY_FIELDS("message.emptyFields"),
    ERROR_DATABASE("message.databaseError"),
    AUTHORIZATION_ERRON("message.authorizationError"),
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
