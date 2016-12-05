package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemindPasswordCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
//        String page = null;
//        HttpSession session = request.getSession();
//        Locale locale = UserPreferences.getLocale();
//        String email = request.getParameter(Parameter.USER_EMAIL);
//        try {
//            UserDto userDto = UserService.getInstance().getByEmail(email);
//            String password = userDto.getPassword();
//            SendMailSSL.getInstance().sendPassword(email, password, locale);
//            String operationResult = OperationMessageManager.PASSWORD_SENT.getLocalised();
//            request.setAttribute(Parameter.OPERATION_MESSAGE, operationResult);
//            page = PagePath.INDEX;
//        }  catch (ServiceException e) {
//            page = PagePath.ERROR;
//            handleServiceException(request, e);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }

}
