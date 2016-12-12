package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.Pagination;
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
public class OrderController {

    @Autowired
    private IUserHotelService userHotelService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private Pagination pagination;

    @RequestMapping(value = Pages.VALUE_SEARCH_HOTELS, method = {RequestMethod.POST, RequestMethod.GET})
    public String searchHotel(OrderDto orderDto, HttpServletRequest request, HttpSession session) {
        String page = null;
        DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
        orderDto.setHotel(destinationDto.getHotelDto());
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
                Long count = userHotelService.getHotelsCount(orderDto);
                Integer currentPage = pagination.getStartRow(request);
                Integer perPage = pagination.getItemPerPage(request);
                Integer totalPages = pagination.getTotalPages(count, perPage, request);
                List<UserHotelDto> userHotelDtoList = userHotelService.getListByOrder(orderDto, currentPage, perPage);
                session.setAttribute(Parameter.SELECTED_USER_HOTEL_LIST, userHotelDtoList);
                session.setAttribute(Parameter.ORDER, orderDto);
                request.setAttribute("command", "nextHotels");
                page = Pages.PAGE_SEARCH_RESULTS;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            BookingExceptionHandler.handleServiceException(e);
        }
        return page;
    }

    @RequestMapping(value = Pages.VALUE_GET_DESTINATIONS, method = RequestMethod.GET)
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
