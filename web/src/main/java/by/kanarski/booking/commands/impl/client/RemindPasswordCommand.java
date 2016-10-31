package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.mail.send.SendMailSSL;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.threadLocal.UserPreferences;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class RemindPasswordCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = UserPreferences.getLocale();
        String email = request.getParameter(Parameter.USER_EMAIL);
        try {
            UserDto userDto = UserServiceImpl.getInstance().getByEmail(email);
            String password = userDto.getPassword();
            SendMailSSL.getInstance().sendPassword(email, password, locale);
            String operationResult = OperationMessageManager.PASSWORD_SENT.getLocalised();
            request.setAttribute(Parameter.OPERATION_MESSAGE, operationResult);
            page = PagePath.INDEX_PAGE_PATH;
        }  catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
