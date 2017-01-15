package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocationToLocationDtoConverter extends EntityConverter implements Converter<Location, LocationDto> {

    @Override
    public LocationDto convert(Location location) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long locationId = location.getLocationId();
        LocationTranslation locationTranslation = location.getLocationTranslationMap().get(languageId);
        String country = locationTranslation.getCountry();
        String city = locationTranslation.getCity();
        String locationStatus = getConversionService().convert(location.getStatus(), String.class);
        return new LocationDto(locationId, country, city, locationStatus);
    }
}
