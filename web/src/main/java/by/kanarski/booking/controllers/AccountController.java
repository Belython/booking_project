package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.Message;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class AccountController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBillService billService;

    private SystemLogger logger = SystemLogger.getInstance().setSender(UserController.class);

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request) {
        logger.logError((Throwable) request.getAttribute(Message.ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
        return Pages.PAGE_ERROR;
    }


    @RequestMapping(value = Pages.VALUE_TO_ACCOUNT, method = RequestMethod.GET)
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

    @RequestMapping(value = Pages.VALUE_CANCEL_BOOKING, method = RequestMethod.GET)
    public String cancelBooking(Long billId, Model model) {
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String page = null;
        try {
            billService.cancelBooking(billId);
            List<BillDto> billList = billService.getByUserId(user.getUserId(), 0, 10);
            model.addAttribute(Parameter.BILL_LIST, billList);
            page = Pages.PAGE_MY_ACCOUNT;
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
            page = Pages.PAGE_ERROR;
        }
        return page;
    }

}