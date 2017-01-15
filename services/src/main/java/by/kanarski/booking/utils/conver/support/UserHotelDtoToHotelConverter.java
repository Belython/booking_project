package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class UserHotelDtoToHotelConverter extends EntityConverter implements Converter<UserHotelDto, Hotel> {

    @Override
    public Hotel convert(UserHotelDto userHotelDto) {
        Long hotelId = userHotelDto.getHotelId();
        LocationDto locationDto = userHotelDto.getLocation();
        Location location = getConversionService().convert(locationDto, Location.class);
        String hotelName = userHotelDto.getHotelName();
        State hotelStatus = getConversionService().convert(userHotelDto.getHotelStatus(), State.class);
        return entityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
    }
}
