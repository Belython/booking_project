package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.SystemLanguagesManager;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocationDtoToLocationConverter implements Converter<LocationDto, Location> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private SystemLanguagesManager systemLanguagesManager;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public Location convert(LocationDto locationDto) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long locationId = location.getLocationId();
        LocationTranslation locationTranslation = location.getLocationTranslationMap().get(languageId);
        String country = locationTranslation.getCountry();
        String city = locationTranslation.getCity();
        String locationStatus = conversionService.convert(location.getLocationStatus(), String.class);
        return new LocationDto(locationId, country, city, locationStatus);
    }
}
