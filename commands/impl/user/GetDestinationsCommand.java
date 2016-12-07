package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelService;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class GetDestinationsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
            List<HotelDto> hotelDtoList = HotelService.getInstance().getByDestination(destinationDto, 0, 5);
            request.setAttribute(Parameter.POSSIBLE_DESTINATIONS, hotelDtoList);
            servletAction = ServletAction.AJAX_INCLUDE_REQUEST;
            page = PagePath.POSSIBLE_DESTINATIONS;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            servletAction = ServletAction.REDIRECT_PAGE;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
