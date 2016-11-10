package by.kanarski.booking.commands.impl.admin.database.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.CommandHelper;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AlterUsersCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page;
        HttpSession session = request.getSession();
        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
        try {
            List<UserDto> userDtoList = RequestParser.parseUserDtoList(request);
            switch (subCommand) {
                case Value.ADD_NEW: {
                    UserServiceImpl.getInstance().addList(userDtoList);
                    break;
                }
                case Value.CHANGE_EXISTING: {
                    UserServiceImpl.getInstance().updateList(userDtoList);
                    break;
                }
            }
            List<UserDto> newUserDtoList = UserServiceImpl.getInstance().getAll();
            session.setAttribute(Parameter.USER_LIST, newUserDtoList);
            String responseText = OperationMessageManager.DATABASE_CHANGE_SUCCES.getLocalised();
            servletAction = CommandHelper.generateServletAction(request, response, responseText);
        } catch (ServiceException e) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ERROR;
            servletAction.setPage(page);
            handleServiceException(request);
        }
        page = servletAction.getPage();
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        return servletAction;
    }
    
}
