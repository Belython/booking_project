package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IFacilityService;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.PaginationUtil;
import by.kanarski.booking.utils.ServletHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@RestController
@RequestMapping("/hotels")
public class HotelsRestController {

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
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchHotel(OrderDto order, HttpServletRequest request, HttpSession session,
                              RedirectAttributes redirectAttributes) {
        String page = null;
//        order = servletHelper.resolveOrder(order, request, session);
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
            BookingExceptionHandler.handleServiceException(e);
            page = Pages.PAGE_ERROR;
        }
        return new ModelAndView(page);
    }

}
