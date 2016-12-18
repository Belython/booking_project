package by.kanarski.booking.filters;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.apache.commons.lang3.LocaleUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig encodingConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Cookie[] cookies = httpServletRequest.getCookies();
        if (locale == null) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(Parameter.COOKIE_LOCALE)) {
                        String localeAsString = cookie.getValue();
                        locale = LocaleUtils.toLocale(localeAsString);
                    }
                }
            }
            if (locale == null) {
                locale = request.getLocale();
                Cookie cookie = new Cookie(Parameter.COOKIE_LOCALE, locale.toString());
                httpServletResponse.addCookie(cookie);
            }
            session.setAttribute(Parameter.CURRENT_LOCALE, locale);
            UserPreferences.setLocale(locale);
        }

        Currency currency = (Currency) session.getAttribute(Parameter.CURRENT_CURRENCY);
        if (currency == null) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(Parameter.COOKIE_CURRENCY)) {
                        String currencyAsString = cookie.getValue();
                        currency = Currency.getInstance(currencyAsString);
                    }
                }
                if (currency == null) {
                    currency = Currency.getInstance(locale);
                    Cookie cookie = new Cookie(Parameter.COOKIE_CURRENCY, currency.toString());
                    httpServletResponse.addCookie(cookie);
                }
            }
            session.setAttribute(Parameter.CURRENT_CURRENCY, currency);
            UserPreferences.setCurrency(currency);
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean containsPreferences = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(Parameter.COOKIE_LOCALE)) {
                    containsPreferences = true;
                }
            }
        }
        return containsPreferences;
    }

}
