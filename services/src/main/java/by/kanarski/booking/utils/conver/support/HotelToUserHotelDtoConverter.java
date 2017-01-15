package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HotelToUserHotelDtoConverter extends EntityConverter implements Converter<Hotel, UserHotelDto> {

    @Override
    public UserHotelDto convert(Hotel hotel) {
        HotelDto hotelDto = getConversionService().convert(hotel, HotelDto.class);
        List<RoomDto> roomDtoSet = getConversionService().convertSetToList(hotel.getRoomSet(), RoomDto.class);
        return new UserHotelDto(hotelDto, roomDtoSet);
    }
}
