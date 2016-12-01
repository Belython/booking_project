package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserHotelService;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToSelectHotelCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            OrderDto orderDto = RequestParser.parseOrderDto(request);
            String hotelName = orderDto.getHotel().getHotelName();
            if (!hotelName.equals(FieldValue.ANY_HOTEL)) {
                // TODO: 29.11.2016 НЕРАБОТАЕТ
//                HotelDto hotelDto = HotelService.getInstance().getByHotelName(orderDto.getHotel());
                HotelDto hotelDto = new HotelDto();
                orderDto.setHotel(hotelDto);
                servletAction = ServletAction.CALL_COMMAND;
                servletAction.setCommandName(CommandType.GOTOSELECTROOMS.name());
            } else {
                List<UserHotelDto> userHotelDtoList = UserHotelService.getInstance().getListByOrder(orderDto, 0, 100);
                session.setAttribute(Parameter.SELECTED_USER_HOTEL_LIST, userHotelDtoList);
                page = PagePath.SELECT_HOTEL;
                servletAction = ServletAction.FORWARD_PAGE;
            }
            session.setAttribute(Parameter.ORDER, orderDto);
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            servletAction = ServletAction.REDIRECT_PAGE;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
