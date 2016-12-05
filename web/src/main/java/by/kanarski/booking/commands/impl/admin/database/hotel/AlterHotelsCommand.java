package by.kanarski.booking.commands.impl.admin.database.hotel;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterHotelsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page;
//        HttpSession session = request.getSession();
//        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
//        try {
//            List<HotelDto> hotelDtoList = RequestParser.parseHotelDtoList(request);
//            switch (subCommand) {
//                case Value.ADD_NEW: {
//                    HotelService.getInstance().addList(hotelDtoList);
//                    break;
//                }
//                case Value.CHANGE_EXISTING: {
//                    HotelService.getInstance().updateList(hotelDtoList);
//                    break;
//                }
//            }
//            List<HotelDto> newHotelDtoList = HotelService.getInstance().getAll(0, 100);
//            session.setAttribute(Parameter.HOTEL_LIST, newHotelDtoList);
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
