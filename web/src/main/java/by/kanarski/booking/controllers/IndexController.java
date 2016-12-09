package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {Pages.VALUE_START, Pages.VALUE_INDEX}, method = {RequestMethod.GET, RequestMethod.POST})
    public String toMainPage() {
//        UserDto userDto = new UserDto();
//        model.addAttribute(Parameter.USER, userDto);
        return Pages.PAGE_INDEX;
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(HttpServletRequest request) {
//        SystemLogger.getInstance().setLogger(getClass(), (Throwable) request.getAttribute(ERROR));
//        request.setAttribute(WebErrorMessages.EXCEPTION_MESSAGE, ERROR_500);
//        return PAGE_ERROR;
//    }
}
