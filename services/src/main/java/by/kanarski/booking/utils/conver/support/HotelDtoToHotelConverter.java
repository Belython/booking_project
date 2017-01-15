package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HotelDtoToHotelConverter extends EntityConverter implements Converter<Hotel, HotelDto> {

    @Override
    public HotelDto convert(Hotel hotel) {
        Long hotelId = hotel.getHotelId();
        Location location = hotel.getLocation();
        LocationDto locationDto = getConversionService().convert(location, LocationDto.class);
        HotelTranslation hotelTranslation = hotel.getHotelTranslationMap().get(getLanguageId());
        String hotelName = hotelTranslation.getHotelName();
        String hotelStatus = getConversionService().convert(hotel.getStatus(), String.class);
        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
    }
}
