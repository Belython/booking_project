package by.kanarski.booking.commands.impl.admin.database.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page;
//        HttpSession session = request.getSession();
//        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
//        try {
//            List<RoomDto> roomDtoList = RequestParser.parseRoomDtoList(request);
//            switch (subCommand) {
//                case Value.ADD_NEW: {
//                    RoomService.getInstance().addList(roomDtoList);
//                    break;
//                }
//                case Value.CHANGE_EXISTING: {
//                    RoomService.getInstance().updateList(roomDtoList);
//                    break;
//                }
//            }
//            List<RoomDto> newRoomDtoList = RoomService.getInstance().getAll(0, 100);
//            session.setAttribute(Parameter.ROOM_LIST, newRoomDtoList);
//            String responseText = OperationMessageManager.DATABASE_CHANGE_SUCCES.getLocalised();
//            servletAction = CommandHelper.generateServletAction(request, response, responseText);
//        } catch (ServiceException e) {
//            servletAction = ServletAction.FORWARD_PAGE;
//            page = PagePath.ERROR;
//            servletAction.setPage(page);
//            handleServiceException(request, e);
//        }
//        page = servletAction.getPage();
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        return servletAction;
    }

}
