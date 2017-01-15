package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomTypeToRoomTypeDtoConverter extends EntityConverter implements Converter<RoomType, RoomTypeDto> {

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
        List<FacilityDto> facilityDtoList = getConversionService().convertSetToList(facilitySet, FacilityDto.class);
        String roomTypeStatus = getConversionService().convert(roomType.getStatus(), String.class);
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilityDtoList, roomTypeStatus);
    }
}
