package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomToRoomDtoConverter extends EntityConverter implements Converter<Room, RoomDto> {

    @Override
    public RoomDto convert(Room room) {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = getConversionService().convert(roomHotel, HotelDto.class);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = getConversionService().convert(roomType, RoomTypeDto.class);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = getConversionService().convert(room.getStatus(), String.class);
        return new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, roomStatus);
    }
}
