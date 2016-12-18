package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.forms.BookRoomsForm;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.*;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.PaginationUtil;
import by.kanarski.booking.utils.RequestParser;
import by.kanarski.booking.utils.ServletHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private IBillService billService;

    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private PaginationUtil paginationUtil;

    @Autowired
    private ServletHelper servletHelper;

    @RequestMapping(value = Pages.PAGE_SEARCH_RESULTS, method = {RequestMethod.POST, RequestMethod.GET})
    public String searchHotel(OrderDto order, HttpServletRequest request, HttpSession session,
                              RedirectAttributes redirectAttributes) {
        String page = null;
        order = servletHelper.resolveOrder(order, request, session);
        try {
            String hotelName = order.getHotel().getHotelName();
            if (StringUtils.isBlank(hotelName)) {
                order.getHotel().setHotelName(FieldValue.ANY_HOTEL);
            }
            if (!hotelName.equals(FieldValue.ANY_HOTEL)) {
                HotelDto hotelDto = order.getHotel();
                HotelDto selectedHotelDto = hotelService.getByDescription(hotelDto);
                redirectAttributes.addAttribute(Parameter.HOTEL_ID, selectedHotelDto.getHotelId());
                page = Pages.REDIRECT_HOTEL;
            } else {
                Long hotelsCount = userHotelService.getHotelsCount(order);
                Integer startRow = paginationUtil.getStartRow(request);
                Integer perPage = paginationUtil.getItemPerPage(request);
                paginationUtil.getTotalPages(hotelsCount, perPage, request);
                List<UserHotelDto> userHotelDtoList = userHotelService.getListByOrder(order, startRow, perPage);
                List<FacilityDto> facilityDtoList = facilityService.getAll();
                session.setAttribute(Parameter.SELECTED_USER_HOTEL_LIST, userHotelDtoList);
                request.setAttribute(Parameter.ALL_ROOM_FACILITIES, facilityDtoList);
                request.setAttribute(Parameter.COMMAND, Pages.PAGE_SEARCH_RESULTS);
                page = Pages.PAGE_SEARCH_RESULTS;
            }
            session.setAttribute(Parameter.ORDER, order);
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            BookingExceptionHandler.handleServiceException(e);
        }
        return page;
    }

    @RequestMapping(value = Pages.VALUE_GET_DESTINATIONS, method = RequestMethod.GET)
    public String getDestinations(HttpServletRequest request, HttpSession session) {
        String page = null;
        try {
            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
            List<HotelDto> hotelDtoList = hotelService.getByDestination(destinationDto, 0, 5);
            request.setAttribute(Parameter.POSSIBLE_DESTINATIONS, hotelDtoList);
            page = Pages.POSSIBLE_DESTINATIONS;
        } catch (ServiceException e) {
            page = Pages.PAGE_ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
        }
        return page;
    }

    @RequestMapping(value = Pages.PAGE_HOTEL, method = RequestMethod.GET)
    public String watchHotel(Long hotelId, HttpServletRequest request, HttpSession session) {
        String page = null;
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        try {
            UserHotelDto userHotelDto = userHotelService.getById(hotelId);
            request.setAttribute(Parameter.SELECTED_USER_HOTEL, userHotelDto);
            orderDto.setUserHotelDto(userHotelDto);
            session.setAttribute(Parameter.ORDER, orderDto);
            page = Pages.PAGE_HOTEL;
        } catch (ServiceException e) {
            page = Pages.PAGE_ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
        }
        return page;
    }

    @RequestMapping(value = Pages.VALUE_BOOK_ROOMS, method = RequestMethod.POST)
    public String bookRooms(BookRoomsForm bookRoomsForm, HttpServletRequest request, HttpSession session) {
        String page = null;
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        try {
            billService.makeBill(bookRoomsForm, orderDto);
            page = Pages.PAGE_HOTEL;
        } catch (ServiceException e) {
            page = Pages.PAGE_ERROR;
//            servletAction = ServletAction.REDIRECT_PAGE;
//            handleServiceException(request, e);
        }
        return page;
    }

}
