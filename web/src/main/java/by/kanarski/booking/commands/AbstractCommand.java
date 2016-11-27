package by.kanarski.booking.commands;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.utils.BookingSystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public abstract class AbstractCommand implements ICommand {

    protected void handleServiceException(HttpServletRequest request, Exception exception) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
//        ResourceBundle bundle = ResourceManager.OPERATION_MESSAGES.setLocale(locale).create();
        String errorMessage = OperationMessageManager.ERROR_DATABASE.getLocalised();
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        BookingSystemLogger.getInstance().logError(getClass(), errorMessage, exception);
    }

    protected void handleServiceException(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
//        ResourceBundle bundle = ResourceManager.OPERATION_MESSAGES.setLocale(locale).create();
        String errorMessage = OperationMessageManager.ERROR_DATABASE.getLocalised();
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
    }

}
