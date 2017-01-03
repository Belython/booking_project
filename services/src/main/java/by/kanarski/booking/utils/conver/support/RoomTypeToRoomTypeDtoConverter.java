package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.SystemLanguagesManager;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomTypeToRoomTypeDtoConverter implements Converter<RoomType, RoomTypeDto> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private SystemLanguagesManager systemLanguagesManager;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public RoomTypeDto convert(RoomType roomType) {
        Long languageId = systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
        Long roomTypeId = roomType.getRoomTypeId();
        RoomTypeTranslation roomTypeTranslation = roomType.getRoomTypeTranslationMap().get(languageId);
        String roomTypeName = roomTypeTranslation.getRoomTypeName();
        Integer maxPersons = roomType.getMaxPersons();
        Double pricePerNightUSD = roomType.getPricePerNight();
        Double pricePerNight = CurrencyUtil.convertFromUSD(pricePerNightUSD, SystemCurrency.DEFAULT);
        Set<Facility> facilitySet = roomType.getFacilitySet();
        List<FacilityDto> facilityDtoList = conversionService.convertSetToList(facilitySet, FacilityDto.class);
        String roomTypeStatus = conversionService.convert(roomType.getRoomTypeStatus(), String.class);
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilityDtoList, roomTypeStatus);
    }
}
