package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.detail.Detail;
import by.kanarski.booking.entities.detail.DetailTranslation;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class DetailToFacilityDtoConverter extends EntityConverter implements Converter<Detail, FacilityDto> {

    @Override
    public FacilityDto convert(Detail detail) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long facilityId = detail.getDetailId();
        DetailTranslation detailTranslation = detail.getDetailTranslationMap().get(languageId);
        String facilityName = detailTranslation.getDetailName();
        String facilityStatus = getConversionService().convert(detail.getStatus(), String.class);
        return new FacilityDto(facilityId, facilityName, facilityStatus);
    }
}
