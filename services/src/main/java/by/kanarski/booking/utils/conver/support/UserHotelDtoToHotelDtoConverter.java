package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class UserHotelDtoToHotelDtoConverter extends EntityConverter implements Converter<UserHotelDto, HotelDto> {

    @Override
    public HotelDto convert(UserHotelDto userHotelDto) {
        Long hotelId = userHotelDto.getHotelId();
        LocationDto locationDto = userHotelDto.getLocation();
        String hotelName = userHotelDto.getHotelName();
        String hotelStatus = userHotelDto.getHotelStatus();
        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
    }
}
