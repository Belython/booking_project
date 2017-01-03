package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.SystemLanguagesManager;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class FacilityDtoToFacilityConverter implements Converter<FacilityDto, Facility> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private SystemLanguagesManager systemLanguagesManager;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public Facility convert(FacilityDto facilityDto) {
        Long facilityId = facilityDto.getFacilityId();
        String facilityName = facilityDto.getFacilityName();
        State facilityStatus = conversionService.convert(facilityDto.getFacilityStatus(), State.class);
        return entityBuilder.buildFacility(facilityId, facilityName, facilityStatus);
    }
}
