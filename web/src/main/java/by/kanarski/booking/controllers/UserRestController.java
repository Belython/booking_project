package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.Message;
import by.kanarski.booking.constants.MessageKey;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.RegistrationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IBillService billService;

    private SystemLogger logger = SystemLogger.getInstance().setSender(UserRestController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto registerUser(@Valid @ModelAttribute(Parameter.USER) UserDto user,
                                BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(Parameter.REGISTRATION_MESSAGE, MessageKey.EMPTY_FIELDS);
            return user;
        }
        try {
            userService.registerUser(user);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        } catch (RegistrationException e) {
            model.addAttribute(Parameter.REGISTRATION_MESSAGE, e.getMessage());
        }
        return user;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String toAccount(Model model) {
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String page = null;
        try {
            List<BillDto> billList = billService.getByUserId(user.getUserId(), 0, 10);
            model.addAttribute(Parameter.BILL_LIST, billList);
            page = Pages.PAGE_MY_ACCOUNT;
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
            page = Pages.PAGE_ERROR;
        }
        return page;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        logger.logError((Throwable) request.getAttribute(Message.ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return Pages.PAGE_ERROR;
    }
}
