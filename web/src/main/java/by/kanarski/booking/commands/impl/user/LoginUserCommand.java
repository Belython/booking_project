package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUserCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            UserDto user = RequestParser.parseUserDto(request);
//            boolean isAuthorised = UserService.getInstance().isAuthorized(user);
//            if (isAuthorised) {
//                user = UserService.getInstance().getByLogin(user.getLogin());
//                session.setAttribute(Parameter.USER, user);
//                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
//                if (page == null) {
//                    page = PagePath.INDEX;
//                }
//            } else {
//                page = PagePath.INDEX;
//                String errorMessage = OperationMessageManager.WRONG_LOGIN_OR_PASSWORD.getLocalised();
//                request.setAttribute(Parameter.OPERATION_MESSAGE, errorMessage);
//            }
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            handleServiceException(request, e);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }

}
