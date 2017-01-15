package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class FacilityToFacilityDtoConverter extends EntityConverter implements Converter<Facility, FacilityDto> {

    @Override
    public FacilityDto convert(Facility facility) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long facilityId = facility.getFacilityId();
        FacilityTranslation facilityTranslation = facility.getFacilityTranslationMap().get(languageId);
        String facilityName = facilityTranslation.getFacilityName();
        String facilityStatus = getConversionService().convert(facility.getStatus(), String.class);
        return new FacilityDto(facilityId, facilityName, facilityStatus);
    }
}
