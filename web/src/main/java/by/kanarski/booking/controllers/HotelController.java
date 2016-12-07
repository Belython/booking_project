package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class HotelController {

    @Autowired
    private IUserHotelService userHotelService;

    @Autowired
    private IHotelService hotelService;

    @RequestMapping(value = Pages.VALUE_SEARCH, method = RequestMethod.POST)
    public String searchHotel(OrderDto orderDto, HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        try {
            String hotelName = orderDto.getHotel().getHotelName();
            if (!hotelName.equals(FieldValue.ANY_HOTEL)) {
                // TODO: 29.11.2016 НЕРАБОТАЕТ
//                HotelDto hotelDto = HotelService.getInstance().getByHotelName(orderDto.getHotel());
//                HotelDto hotelDto = new HotelDto();
//                orderDto.setHotel(hotelDto);
//                servletAction = ServletAction.CALL_COMMAND;
//                servletAction.setCommandName(CommandType.GOTOSELECTROOMS.name());
            } else {
                List<UserHotelDto> userHotelDtoList = userHotelService.getListByOrder(orderDto, 0, 100);
                session.setAttribute(Parameter.SELECTED_USER_HOTEL_LIST, userHotelDtoList);
                page = PagePath.SEARCH_RESULTS;
//                servletAction = ServletAction.FORWARD_PAGE;
            }
            session.setAttribute(Parameter.ORDER, orderDto);
        } catch (ServiceException e) {
            page = PagePath.ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return Pages.VALUE_SEARCH;
    }

    @RequestMapping(value = Pages.VALUE_GET_DESTINATIONS, method = RequestMethod.POST)
    public String getDestinations(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        try {
            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
            List<HotelDto> hotelDtoList = hotelService.getByDestination(destinationDto, 0, 5);
            request.setAttribute(Parameter.POSSIBLE_DESTINATIONS, hotelDtoList);
            page = PagePath.POSSIBLE_DESTINATIONS;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return "possibleDestinations";
    }

}
