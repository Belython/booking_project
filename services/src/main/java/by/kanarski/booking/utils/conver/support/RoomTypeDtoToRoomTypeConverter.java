package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.SystemLanguagesManager;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomTypeDtoToRoomTypeConverter implements Converter<RoomTypeDto, RoomType> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private SystemLanguagesManager systemLanguagesManager;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public RoomType convert(RoomTypeDto roomTypeDto) {
        Long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        Integer maxPersons = roomTypeDto.getMaxPersons();
        Double pricePerNight = roomTypeDto.getPricePerNight();
        Double pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, SystemCurrency.DEFAULT);
        List<FacilityDto> facilityDtoList = roomTypeDto.getFacilityList();
        Set<Facility> facilitySet = conversionService.convertListToSet(facilityDtoList, Facility.class);
        State roomTypeStatus = conversionService.convert(roomTypeDto.getRoomTypeStatus(), State.class);
        return entityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitySet, roomTypeStatus);
    }
}
