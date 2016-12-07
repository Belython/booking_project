package by.kanarski.booking.commands.impl.admin.database.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.RoomService;
import by.kanarski.booking.utils.CommandHelper;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AlterRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page;
        HttpSession session = request.getSession();
        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
        try {
            List<RoomDto> roomDtoList = RequestParser.parseRoomDtoList(request);
            switch (subCommand) {
                case Value.ADD_NEW: {
                    RoomService.getInstance().addList(roomDtoList);
                    break;
                }
                case Value.CHANGE_EXISTING: {
                    RoomService.getInstance().updateList(roomDtoList);
                    break;
                }
            }
            List<RoomDto> newRoomDtoList = RoomService.getInstance().getAll(0, 100);
            session.setAttribute(Parameter.ROOM_LIST, newRoomDtoList);
            String responseText = OperationMessageManager.DATABASE_CHANGE_SUCCES.getLocalised();
            servletAction = CommandHelper.generateServletAction(request, response, responseText);
        } catch (ServiceException e) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ERROR;
            servletAction.setPage(page);
            handleServiceException(request, e);
        }
        page = servletAction.getPage();
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        return servletAction;
    }

}
