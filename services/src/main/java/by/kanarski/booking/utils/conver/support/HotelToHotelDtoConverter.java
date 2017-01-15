package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HotelToHotelDtoConverter extends EntityConverter implements Converter<HotelDto, Hotel> {

    @Override
    public Hotel convert(HotelDto hotelDto) {
        Long hotelId = hotelDto.getHotelId();
        LocationDto locationDto = hotelDto.getLocation();
        Location location = getConversionService().convert(locationDto, Location.class);
        String hotelName = hotelDto.getHotelName();
        State hotelStatus = getConversionService().convert(hotelDto.getHotelStatus(), State.class);
        String laguage = hotelDto.getLanguage();
        Long languageId = systemLanguagesManager.getLanguageId(laguage);
        return entityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
    }
}
