package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        SystemLogger.getInstance().logError(getClass(), (Throwable) request.getAttribute(Message.ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return Pages.PAGE_ERROR;
    }

    @RequestMapping(value = Pages.VALUE_LOGIN, method = RequestMethod.POST)
    public String loginUser(UserDto unauthorizedUser, Model model, HttpServletRequest request) throws ServiceException {
        String page = null;
        String login = unauthorizedUser.getLogin();
        String password = unauthorizedUser.getPassword();
                boolean isAuthorised = userService.isAuthorized(unauthorizedUser);
                if (isAuthorised) {
                    UserDto authorizedUser = userService.loginUser(unauthorizedUser);
                    if (authorizedUser == null) {
                        model.addAttribute(UIParams.WRONG_USER_LOGIN, Message.ERROR_LOGIN);
                        page = Pages.PAGE_INDEX;
                    }
                    model.addAttribute(Parameter.USER, authorizedUser);
                    HttpSession session = request.getSession();
                    session.setAttribute(Parameter.USER, authorizedUser);
                    page = Pages.PAGE_INDEX;
                }
                // TODO: 05.12.2016 что-нибудь связанное с авторизацией
            }
            model.addAttribute(UIParams.PASSWORD_ERROR, Message.PASSWORD_ERROR_I18N);
            page = Pages.PAGE_INDEX;
        }
        model.addAttribute(UIParams.LOGIN_ERROR, Message.LOGIN_ERROR_I18N);
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_LOGOUT)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Parameter.USER);
        return Pages.PAGE_INDEX;
    }

    @RequestMapping(value = Pages.VALUE_NEW_USER, method = RequestMethod.POST)
    public String createUser(UserDto userDto, BindingResult result, Model model,
                             HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return Pages.PAGE_REGISTRATION;
        }
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        if (!login.matches(RegExp.LOGIN)) {
            model.addAttribute(UIParams.LOGIN_ERROR, Message.LOGIN_ERROR_I18N);
        }

        try {
            client.setStatusOfClient(statusOfClientService.get(ClientStatus.class, 1));
            client.setRole(roleService.get(Roles.class, 1));
            clientServices.save(client);
            model.addAttribute(CLIENT, client);
            redirectAttributes.addFlashAttribute(UIParams.REQUEST_SUCCESS_REGISTRY, Message.SUCCESS_REGISTRY);
        } catch (ServiceException e) {
            SystemLogger.getInstance().setLogger(getClass(), e);
            model.addAttribute(UIParams.SERVICE_EXCEPTION, e.getMessage());
        }
        return REDIRECT_INDEX;

    }


}
