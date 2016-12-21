package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.exceptions.RegistrationException;
import by.kanarski.booking.mail.send.SendMailSSL;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.SystemLogger;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Currency;
import java.util.Locale;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;
    @Autowired
    private MessageSource messageSource;

    private SystemLogger logger = SystemLogger.getInstance().setSender(UserController.class);

    @RequestMapping(value = Pages.PAGE_INDEX, method = {RequestMethod.GET, RequestMethod.POST})
    public String toMainPage() {
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_LOGIN, method = {RequestMethod.GET, RequestMethod.POST})
    public String loginUser(String error, Model model, HttpSession session) throws ServiceException {
        if (error != null) {
            model.addAttribute(Parameter.LOGIN_OPERATION_MESSAGE, MessageKey.WRONG_LOGIN_OR_PASSWORD);
        }
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        return currentViewName;
    }

    @RequestMapping(value = Pages.VALUE_REGISTER_USER, method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute(Parameter.USER) UserDto user,
                               BindingResult bindingResult, Model model, HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        if (bindingResult.hasErrors()) {
            model.addAttribute(Parameter.REGISTRATION_MESSAGE, MessageKey.EMPTY_FIELDS);
            return currentViewName;
        }
        try {
            userService.registerUser(user);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        } catch (RegistrationException e) {
            model.addAttribute(Parameter.REGISTRATION_MESSAGE, e.getMessage());
        }
        return Pages.REDIRECT_START;
    }

    @RequestMapping(value = Pages.VALUE_SET_LOCALE, method = RequestMethod.GET)
    public String setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response,
                            HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        Cookie cookie = new Cookie(Parameter.COOKIE_LOCALE, locale.toString());
        response.addCookie(cookie);
        session.setAttribute(Parameter.CURRENT_LOCALE, locale);
        UserPreferences.setLocale(locale);
        return currentViewName;
    }

    @RequestMapping(value = Pages.VALUE_SET_CURRENCY, method = RequestMethod.GET)
    public String setCurrency(Currency currency, HttpServletRequest request, HttpServletResponse response,
                              HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);
        Cookie cookie = new Cookie(Parameter.COOKIE_CURRENCY, currency.toString());
        response.addCookie(cookie);
        session.setAttribute(Parameter.CURRENT_CURRENCY, currency);
        UserPreferences.setCurrency(currency);
        return currentViewName;
    }

    @RequestMapping(value = "remindPassword", method = RequestMethod.POST)
    public String remindPassword(String email, Model model, Locale locale) {
        try {
            UserDto userDto = userService.getByEmail(email);
            String password = userDto.getPassword();
            SendMailSSL.getInstance().sendPassword(email, password, locale);
            String message = getMessage(MessageKey.PASSWORD_SENT, locale);
            model.addAttribute(Parameter.REMIND_PASSWORD_MESSAGE, message);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.PAGE_404, method = {RequestMethod.GET, RequestMethod.POST})
    public String toEror() {
//        UserDto userDto = new UserDto();
//        model.addAttribute(Parameter.USER, userDto);
        return Pages.PAGE_ERROR;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        logger.logError((Throwable) request.getAttribute(Message.ERROR));
        return Pages.PAGE_ERROR;
    }

    private String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

}
