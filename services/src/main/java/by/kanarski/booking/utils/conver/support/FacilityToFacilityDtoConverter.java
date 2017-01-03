package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
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

public class FacilityToFacilityDtoConverter implements Converter<Facility, FacilityDto> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private SystemLanguagesManager systemLanguagesManager;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public FacilityDto convert(Facility facility) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long facilityId = facility.getFacilityId();
        FacilityTranslation facilityTranslation = facility.getFacilityTranslationMap().get(languageId);
        String facilityName = facilityTranslation.getFacilityName();
        String facilityStatus = conversionService.convert(facility.getFacilityStatus(), String.class);
        return new FacilityDto(facilityId, facilityName, facilityStatus);
    }
}