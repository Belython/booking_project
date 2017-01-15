package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomDtoToRoomConverter extends EntityConverter implements Converter<RoomDto, Room> {

    @Override
    public Room convert(RoomDto roomDto) {
        Long roomId = roomDto.getRoomId();
        Hotel hotel = getConversionService().convert(roomDto.getHotel(), Hotel.class);
        RoomType roomType = getConversionService().convert(roomDto.getRoomType(), RoomType.class);
        Integer roomNumber = roomDto.getRoomNumber();
        State roomStatus = getConversionService().convert(roomDto.getRoomStatus(), State.class);
        return entityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, roomStatus);
    }
}
