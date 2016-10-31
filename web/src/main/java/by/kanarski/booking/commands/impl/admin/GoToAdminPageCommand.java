package by.kanarski.booking.commands.impl.admin;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoToAdminPageCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        UserDto admin = (UserDto) session.getAttribute(Parameter.USER);
        if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ADMIN_MAIN_PAGE_PATH;
        } else {
            String opertaionMessage = OperationMessageManager.LOW_ACCESS_LEVEL.getLocalised();
            request.setAttribute(Parameter.OPERATION_MESSAGE, opertaionMessage);
            servletAction = ServletAction.AJAX_REQUEST;
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
