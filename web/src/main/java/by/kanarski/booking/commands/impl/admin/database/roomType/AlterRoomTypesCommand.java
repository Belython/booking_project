package by.kanarski.booking.commands.impl.admin.database.roomType;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterRoomTypesCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page;
//        HttpSession session = request.getSession();
//        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
//        try {
//            List<RoomTypeDto> roomTypeDtoList = RequestParser.parseRoomTypeDtoList(request);
//
//            // TODO: 07.11.2016 незнаю что это значит, пока уберу
//
////            for (int i = 0; i < roomTypeDtoList.size(); i++) {
////                RoomTypeDto roomTypeDto = roomTypeDtoList.get(i);
////                if (roomTypeDto == null || roomTypeDto.getRoomTypeName() == null) {
////                    roomTypeDtoList.remove(i);
////                }
////            }
//            switch (subCommand) {
//                case Value.ADD_NEW: {
//                    RoomTypeService.getInstance().addList(roomTypeDtoList);
//                    break;
//                }
//                case Value.CHANGE_EXISTING: {
//                    RoomTypeService.getInstance().updateList(roomTypeDtoList);
//                    break;
//                }
//            }
//            List<RoomTypeDto> newRoomTypeDtoList = RoomTypeService.getInstance().getAll(0, 100);
//            session.setAttribute(Parameter.ROOM_TYPE_LIST, newRoomTypeDtoList);
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
