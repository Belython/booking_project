package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.location.Location;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocationDtoToLocationConverter extends EntityConverter implements Converter<LocationDto, Location> {

    @Override
    public Location convert(LocationDto locationDto) {
        Long locationId = locationDto.getLocationId();
        String country = locationDto.getCountry();
        String city = locationDto.getCity();
        State locationStatus = getConversionService().convert(locationDto.getLocationStatus(), State.class);
        String language = locationDto.getLanguage();
        return entityBuilder.buildLocation(locationId, country, city, locationStatus);
    }
}
