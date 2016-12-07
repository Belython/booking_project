package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.i18n.l10n.filler.factory.FillerFactory;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.SystemLogger;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        SystemLogger.getInstance().logError(getClass(), (Throwable) request.getAttribute(Message.ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return Pages.PAGE_ERROR;
    }

    @RequestMapping(value = Pages.VALUE_LOGIN, method = RequestMethod.POST)
    public String loginUser(UserDto unauthorizedUser, Model model, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        String login = unauthorizedUser.getLogin();
        String password = unauthorizedUser.getPassword();
        if ((!login.matches(RegExp.LOGIN)) || (!password.matches(RegExp.PASSWORD))) {
            model.addAttribute("login_or_password_error", "login_or_password_error");
        } else {
            boolean isAuthorised = userService.isAuthorized(unauthorizedUser);
            if (isAuthorised) {
                UserDto authorizedUser = userService.loginUser(unauthorizedUser);
                if (authorizedUser == null) {
                    model.addAttribute("login_or_password_error", "login_or_password_error");
                } else {
                    model.addAttribute(Parameter.USER, authorizedUser);
                    session.setAttribute(Parameter.USER, authorizedUser);
                }
            }
            // TODO: 05.12.2016 что-нибудь связанное с авторизацией
        }
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_LOGOUT)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Parameter.USER);
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.REGISTER_USER, method = RequestMethod.POST)
    public String registerUser(UserDto userDto, BindingResult result, Model model,
                               HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String page = null;
//        if (result.hasErrors()) {
//            return Pages.PAGE_REGISTRATION;
//        }
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String email = userDto.getEmail();

//        if (!login.matches(RegExp.LOGIN)) {
//            model.addAttribute("not_valid_login_error", "not_valid_login_error");
//        }
//        if (!password.matches(RegExp.PASSWORD)) {
//            model.addAttribute("not_valid_password_error", "not_valid_password_error");
//        }
        userDto.setRole(FieldValue.ROLE_CLIENT);
        userDto.setUserStatus(FieldValue.STATUS_ACTIVE);
        try {
            userService.registerUser(userDto);
//            model.addAttribute(Parameter.USER, userDto);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_REGISTRY, Message.SUCCESS_REGISTRY);
        } catch (ServiceException e) {
//            HttpSession session = request.getSession();
//            Locale locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
////        ResourceBundle bundle = ResourceManager.OPERATION_MESSAGES.setLocale(locale).create();
//            String errorMessage = OperationMessageManager.ERROR_DATABASE.getLocalised();
//            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
//            SystemLogger.getInstance().logError(getClass(), errorMessage, exception);
//            SystemLogger.getInstance().setLogger(getClass(), e);
//            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
        }
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_SET_LOCALE, method = RequestMethod.GET)
    public String setLocale(Locale locale, HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
//        Locale currentLocale = new Locale(locale);
        session.setAttribute(Parameter.CURRENT_LOCALE, locale);
        UserPreferences.setLocale(locale);
        page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        Filler filler = FillerFactory.getInstance().defineFiller(page);
        filler.fill(request);
        page = Pages.PAGE_INDEX;
        return page;
    }

    @RequestMapping(value = Pages.VALUE_INDEX, method = RequestMethod.GET)
    public String toMainPage() {
        return Pages.PAGE_INDEX;
    }


}
