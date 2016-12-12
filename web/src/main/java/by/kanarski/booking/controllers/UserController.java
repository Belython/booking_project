package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.SystemLogger;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.Locale;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    private SystemLogger logger = SystemLogger.getInstance().setSender(UserController.class);

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        logger.logError((Throwable) request.getAttribute(Message.ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return Pages.PAGE_ERROR;
    }

    @RequestMapping(value = Pages.VALUE_LOGIN, method = RequestMethod.GET)
    public String loginUser(String error, Model model) throws ServiceException {
        if (error != null) {
            model.addAttribute(UIParameter.LOGIN_OPERATION_MESSAGE, MessageKey.WRONG_LOGIN_OR_PASSWORD);
        }
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return Pages.REDIRECT_INDEX;
    }

    @RequestMapping(value = Pages.REGISTER_USER, method = RequestMethod.POST)
    public String registerUser(UserDto userDto, BindingResult bindingResult, HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        if (bindingResult.hasErrors()) {
            return currentViewName;
        }
        userDto.setRole(FieldValue.ROLE_USER);
        userDto.setUserStatus(FieldValue.STATUS_ACTIVE);
        try {
            userService.registerUser(userDto);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_SET_LOCALE, method = RequestMethod.GET)
    public String setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response,
                            HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        Cookie cookie = new Cookie(UIParameter.COOKIE_LOCALE, locale.toString());
        response.addCookie(cookie);
        session.setAttribute(Parameter.CURRENT_LOCALE, locale);
        UserPreferences.setLocale(locale);
//        Filler filler = FillerFactory.getInstance().defineFiller(PagePath.INDEX);
//        filler.fill(request);
        return currentViewName;
    }

    @RequestMapping(value = Pages.VALUE_SET_CURRENCY, method = RequestMethod.GET)
    public String setLocale(Currency currency, HttpServletRequest request, HttpServletResponse response,
                            HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        Cookie cookie = new Cookie(UIParameter.COOKIE_CURRENCY, currency.toString());
        response.addCookie(cookie);
        session.setAttribute(Parameter.CURRENT_CURRENCY, currency);
        UserPreferences.setCurrency(currency);
//        Filler filler = FillerFactory.getInstance().defineFiller(PagePath.INDEX);
//        filler.fill(request);
        return currentViewName;
    }

}
