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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IBillService billService;

    private SystemLogger logger = SystemLogger.getInstance().setSender(UserController.class);

    @RequestMapping(value = Pages.VALUE_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return Pages.REDIRECT_START;
    }

    @RequestMapping(value = Pages.PAGE_MY_ACCOUNT, method = {RequestMethod.GET, RequestMethod.POST})
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

    @RequestMapping(value = Pages.VALUE_PAY_BILL, method = RequestMethod.GET)
    public String payBIll(Long billId, Model model) {
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String page = null;
        try {
            billService.payBIll(billId);
            List<BillDto> billList = billService.getByUserId(user.getUserId(), 0, 10);
            model.addAttribute(Parameter.BILL_LIST, billList);
            page = Pages.PAGE_MY_ACCOUNT;
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
            page = Pages.PAGE_ERROR;
        }
        return page;
    }

    @RequestMapping(value = Pages.VALUE_REMOVE_BILL, method = RequestMethod.GET)
    public String deleteBill(Long billId, Model model) {
        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String page = null;
        try {
            billService.deleteBill(billId);
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
