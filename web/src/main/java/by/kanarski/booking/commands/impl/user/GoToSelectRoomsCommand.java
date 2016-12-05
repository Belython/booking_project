package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToSelectRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
//        String page;
//        HttpSession session = request.getSession();
//        try {
//            OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
//            HotelDto hotelDto = orderDto.getHotel();
//            Long hotelId = hotelDto.getHotelId();
//            if (hotelId == null) {
//                hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
//                hotelDto = HotelService.getInstance().getById(hotelId);
//                orderDto.setHotel(hotelDto);
//                session.setAttribute(Parameter.ORDER, orderDto);
//            }
//            UserHotelDto selectedUserHotelDto = UserHotelService.getInstance().getByOrder(orderDto);
//            session.setAttribute(Parameter.SELECTED_USER_HOTEL, selectedUserHotelDto);
//            page = PagePath.SELECT_ROOMS;
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
