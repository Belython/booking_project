package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToSearchResultsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            OrderDto orderDto = RequestParser.parseOrderDto(request);
//            String hotelName = orderDto.getHotel().getHotelName();
//            if (!hotelName.equals(FieldValue.ANY_HOTEL)) {
//                // TODO: 29.11.2016 НЕРАБОТАЕТ
////                HotelDto hotelDto = HotelService.getInstance().getByHotelName(orderDto.getHotel());
//                HotelDto hotelDto = new HotelDto();
//                orderDto.setHotel(hotelDto);
//                servletAction = ServletAction.CALL_COMMAND;
//                servletAction.setCommandName(CommandType.GOTOSELECTROOMS.name());
//            } else {
//                List<UserHotelDto> userHotelDtoList = UserHotelService.getInstance().getListByOrder(orderDto, 0, 100);
//                session.setAttribute(Parameter.SELECTED_USER_HOTEL_LIST, userHotelDtoList);
//                page = PagePath.SEARCH_RESULTS;
//                servletAction = ServletAction.FORWARD_PAGE;
//            }
//            session.setAttribute(Parameter.ORDER, orderDto);
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }

}
