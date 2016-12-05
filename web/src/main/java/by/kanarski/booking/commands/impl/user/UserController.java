package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = VALUE_LOGIN, method = RequestMethod.POST)
    public String loginUser(UserDto unauthorizedUser, Model model, HttpServletRequest request) throws ServiceException {
        String login = unauthorizedUser.getLogin();
        String password = unauthorizedUser.getPassword();
        if (login.matches(RegExp.LOGIN)) {
            if (password.matches(RegExp.PASSWORD)) {
                boolean isAuthorised = userService.isAuthorized(unauthorizedUser);
                if (isAuthorised) {
                    UserDto authorizedUser = userService.loginUser(unauthorizedUser);
                    if (authorizedUser == null) {
                        model.addAttribute(UIParams.WRONG_USER_LOGIN, Message.ERROR_LOGIN);
                        return Pages.PAGE_INDEX;
                    }
                    model.addAttribute(Parameter.USER, authorizedUser);
                    HttpSession session = request.getSession();
                    session.setAttribute(Parameter.USER, authorizedUser);
                    return Pa;
                }
                // TODO: 05.12.2016 что-нибудь связанное с авторизацией
            }
            model.addAttribute(PASSWORD_ERROR, PASSWORD_ERROR_I18N);
            return PAGE_INDEX;
        }
        model.addAttribute(EMAIL_ERROR, EMAIL_ERROR_I18N);
        return PAGE_INDEX;
    }

}
