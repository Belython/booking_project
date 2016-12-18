package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
public class ServletHelper {

    public OrderDto resolveOrder(OrderDto order, HttpServletRequest request, HttpSession session) {
        if (order.getCheckInDate() == null) {
            order = (OrderDto) session.getAttribute(Parameter.ORDER);
        } else if (order.getHotel() == null) {
            DestinationDto destinationDto = RequestParser.parseDestinationDto(request);
            order.setHotel(destinationDto.getHotelDto());
        }
        String roomFacilities = order.getRoomFacilities();
        if (StringUtils.isNotBlank(roomFacilities)) {
            List<String> facilityList = new ArrayList<>();
            String[] facilityArray = StringUtils.split(roomFacilities, ", ");
            Collections.addAll(facilityList, facilityArray);
            order.setFacilityList(facilityList);
        }
        return order;
    }

}
