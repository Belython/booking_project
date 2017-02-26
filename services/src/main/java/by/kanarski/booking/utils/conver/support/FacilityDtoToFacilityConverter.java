package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.detail.Detail;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class FacilityDtoToFacilityConverter extends EntityConverter implements Converter<FacilityDto, Detail> {

    @Override
    public Detail convert(FacilityDto facilityDto) {
        Long facilityId = facilityDto.getFacilityId();
        String facilityName = facilityDto.getFacilityName();
        State facilityStatus = getConversionService().convert(facilityDto.getFacilityStatus(), State.class);
        return entityBuilder.buildFacility(facilityId, facilityName, facilityStatus);
    }
}
