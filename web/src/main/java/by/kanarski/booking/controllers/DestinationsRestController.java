package by.kanarski.booking.controllers;

import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.services.interfaces.IFacilityService;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.PaginationUtil;
import by.kanarski.booking.utils.RequestParser;
import by.kanarski.booking.utils.ServletHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@RestController
@RequestMapping("/destinations")
public class DestinationsRestController {

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
    public ResponseEntity<List<HotelDto>> getDestinations(HttpServletRequest request, HttpSession session) {
//        String page = null;
        ResponseEntity<List<HotelDto>> responseEntity = null;
        try {
            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
            List<HotelDto> hotelDtoList = hotelService.getByDestination(destinationDto, 0, 5);
            responseEntity = new ResponseEntity<>(hotelDtoList, HttpStatus.OK);
//            request.setAttribute(Parameter.POSSIBLE_DESTINATIONS, hotelDtoList);
//            page = Pages.POSSIBLE_DESTINATIONS;
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
//            page = Pages.PAGE_ERROR;
        }
        return responseEntity;
    }

}
