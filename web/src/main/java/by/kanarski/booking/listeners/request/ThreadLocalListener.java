package by.kanarski.booking.listeners.request;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;
import by.kanarski.booking.utils.threadLocal.UserPreferences;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.Locale;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ThreadLocalListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ThreadLocalUtil.destroy();

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        request.setAttribute(Parameter.USER, new UserDto());
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENT_CURRENCY);
        UserPreferences.setLocale(locale);
        UserPreferences.setCurrency(currency);
    }
}
