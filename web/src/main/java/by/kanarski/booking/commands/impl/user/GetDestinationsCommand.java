package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class GetDestinationsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
//            List<HotelDto> hotelDtoList = HotelService.getInstance().getByDestination(destinationDto, 0, 5);
//            request.setAttribute(Parameter.POSSIBLE_DESTINATIONS, hotelDtoList);
//            servletAction = ServletAction.AJAX_INCLUDE_REQUEST;
//            page = PagePath.POSSIBLE_DESTINATIONS;
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
