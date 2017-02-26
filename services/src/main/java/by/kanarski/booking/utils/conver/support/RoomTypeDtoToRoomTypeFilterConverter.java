package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.detail.Detail;
import by.kanarski.booking.entities.roomType.RoomTypeFilter;
import by.kanarski.booking.utils.CurrencyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomTypeDtoToRoomTypeFilterConverter extends EntityConverter implements Converter<RoomTypeDto, RoomTypeFilter> {

    @Override
    public RoomTypeFilter convert(RoomTypeDto roomTypeDto) {
        String roomTypeName = roomTypeDto.getRoomTypeName();
        roomTypeName = (StringUtils.isEmpty(roomTypeName)) ? FieldValue.ANY_ROOM_TYPE : roomTypeName;
        Integer maxPersons = roomTypeDto.getMaxPersons();
        Double pricePerNight = roomTypeDto.getPricePerNight();
        Double pricePerNightUSD = null;
        if (pricePerNight != null) {
            pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, SystemCurrency.DEFAULT);
        }
        List<FacilityDto> facilityDtoList = roomTypeDto.getFacilityList();
        Set<Detail> detailSet = null;
        if (CollectionUtils.isNotEmpty(facilityDtoList)) {
            detailSet = getConversionService().convertListToSet(facilityDtoList, Detail.class);
        }
        State roomTypeStatus = getConversionService().convert(roomTypeDto.getRoomTypeStatus(), State.class);
        return entityBuilder.buildRoomTypeFilter(roomTypeName, maxPersons, pricePerNightUSD,
                detailSet, roomTypeStatus);
    }
}
